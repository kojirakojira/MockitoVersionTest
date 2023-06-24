package testGroup.test;

public class MockitoInspection {

	public String execute() {
		MockTarget mockTarget = new MockTarget();
		String str = mockTarget.calc(3);
		return str;
	}
}
