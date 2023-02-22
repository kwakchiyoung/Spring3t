package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L; //static이기때문에 무조건 하나씩 생성됨.

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){

    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store자체를 보호하기 위해 다시 생성해서 넣어준듯.
    }

    public void clearStore() {
        store.clear();
    }
}
