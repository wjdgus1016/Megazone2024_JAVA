package ch08;



public class ExceptionEx5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			install();
		} catch (InstallException e) {
			System.out.println("에러 메세지 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			deleteTempFiles();
		}
		
	}

	static void install() throws InstallException {
		try {
			startInstall();
		} catch (SpaceException se) {
			InstallException ie = new InstallException("설치중 예외발생");
			ie.initCause(se);
			throw ie;
		} catch (MemoryException me) {
			InstallException ie = new InstallException("설치중 예외발생");
			ie.initCause(me);
			throw ie;
		} 
		
	}
	
	
	// 프로그램 설치와 관련된 메소드 작성
	static void startInstall() throws SpaceException, MemoryException {
		if(!enoughSpace())
			throw new SpaceException("설치 실패 : 설치공간 부족");
		
		if(!enoughMemory())
			throw new MemoryException("설치 실패 : 메모리 여유 부족");
	}
	
	static void copyFiles() {}
	
	static void deleteTempFiles() {
		System.out.println("설치 파일 삭제 완료");
	}
	
	static boolean enoughSpace() {
		// 디스크 여유 공간 체크 로직 작성.
		// false : 공간이 부족. => 예외 발생 시켜야 하는 상황.
		// true : 디스크의 설치 공간이 충분 => 예외 발생 하지 않음.
		return true;
	}
	
	static boolean enoughMemory() {
		// 메모리 여유 공간 체크 로직 작성.
		return false;
	}
}


// 예외 클래스 정의

// 예외 클래스 카테고리
/**
 * - 사용자 예외 - 100 개
 * - 시스템 예외 - 100 개
 * - 서비스 예외 - 100 개
 * - 운영 예외 - 100 개
 * 
 * - 400개 예외에 대해서 try{} catch(){} 초반에는 엄격하게 하는게 맞을 수 있지만,
 *   정말 안정화 되고, 운영이 잘되고 있는 상황에서, 신규개발시, 수정시
 *   try{} catch(){} 를 하지 않아도 되도록 개발 및 유지보수 정책을 수립할 수 있음.
 *   
 *   RuntimeException class 를 사용하게 되면, try{} catch(){} 를 하지 않아도
 *   되게 해줌.
 *   
 * - checked, unchecked 옵션
 *   checked : 컴파일러가 검사 -> Exception 의 관계를 맺어주면 됨. -> 상속
 *   		예외 처리를 강제 하겠다는 의미. -> 직접 예외 처리 or 위임 처리
 *   unchecked : 컴파일러가 검사 안함 -> RuntimeException 의 관계를 맺어주면 됨. -> 상속
 *   
 *   
 * 
 * 
 * 
 */


// 설치 예외
class InstallException extends Exception {
	public InstallException(String msg) {
		super(msg);
	}
}


// 설치 예외의 원인 예외(디스크 용량)
class SpaceException extends Exception {
	public SpaceException(String msg) {
		super(msg);
	}
}

//설치 예외의 원인 예외(메모리 용량)
class MemoryException extends Exception {
	public MemoryException(String msg) {
		super(msg);
	}
}









