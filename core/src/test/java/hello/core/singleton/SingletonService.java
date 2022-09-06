package hello.core.singleton;

public class SingletonService {

    //1. static 영역에 객체를 1개만 생성해둔다.
    private static final SingletonService instance = new SingletonService();

    //2. public으로 열어 객체 인스턴스가 필요하면 이 static 매서드를 통해서만 조회하도록 허용한다
    public static SingletonService getInstance() {
        return instance;
    }

    //3. 생성자를 private로 선언해 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다
    private SingletonService() {

    }

}
