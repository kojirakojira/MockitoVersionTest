package testGroup.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

/**
 * Java, Junit, Mockitoのバージョンでインスタンス生成時にモック化ができるかを確認する。
 */
public class MockitoInspectionTest {

	@Test
	public void test() {

		// 引数監視用。（Integerを参照渡しさせるために一旦リストで…）
		List<Integer> argList = new ArrayList<Integer>();

		// モック化
		MockedConstruction<MockTarget> mocked = Mockito.mockConstruction(
				MockTarget.class,
				(mock, context) -> {
					Mockito.doAnswer(inv -> {
						// 引数を出力
						argList.add(inv.getArgument(0));
						return "10";
					}).when(mock).calc(Mockito.anyInt());
				});

		// テスト実行
		MockitoInspection testTarget = new MockitoInspection();
		String ret = testTarget.execute();

		// モック化終了
		mocked.close();

		assertEquals("10", ret); // ソースコード的にモック化できてなかったら8が返ってくる。10ならモック化できている。
		assertEquals(3, argList.get(0)); // 引数には3が渡ってくる。モックで引数が取得できていることの確認。
	}
}
