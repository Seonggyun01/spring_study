package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();   //공유 변수의 경우 그냥 Map 사용 시 동시성 문제 발생 실무에서는 ConcurrentHashMap, AtomicLong 사용으로 동시성 문제 고려함.
    private static Long sequence = 0L;  //실무에서는 AtomicLong 사용

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findbyName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();    // Map을 다 지우는 함수
    }
}
