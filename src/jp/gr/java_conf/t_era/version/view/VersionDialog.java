package jp.gr.java_conf.t_era.version.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.xml.sax.SAXException;

import jp.gr.java_conf.t_era.version.model.version.VersionInfo;
import jp.gr.java_conf.t_era.version.view.parts.VersionTreeViewer;

/**
 * ヴァージョン情報を表示するダイアログを作成するファクトリ
 * @author y-terada
 *
 */
public class VersionDialog{
	/**
	 * 指定したバージョン情報を表示するダイアログを生成し、返します。
	 * @param parent ダイアログの親フレーム
	 * @param title ダイアログタイトル
	 * @param version バージョン情報
	 * @return ダイアログインスタンス
	 */
	public static JDialog getDialog(JFrame parent, String title, VersionInfo version) {
		JDialog dialog = new JDialog(parent, title);

		Container con = dialog.getContentPane();
		con.setLayout(new BorderLayout());
		if (version.getAppName() != null) {
			con.add(new JLabel(version.getAppName()), BorderLayout.NORTH);
		}
		Component view = VersionTreeViewer.getTreeViewer(version);
		JScrollPane scrollPane = new JScrollPane(view, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		con.add(scrollPane, BorderLayout.CENTER);

		dialog.pack();
		return dialog;
	}

	/**
	 * 指定したファイルからバージョン情報を読み出し、表示するダイアログを生成し、返します。
	 * @param parent ダイアログの親フレーム
	 * @param title ダイアログタイトル
	 * @param xmlFile バージョン情報を記載したファイル
	 * @return ダイアログインスタンス
	 */
	public static JDialog getDialog(JFrame parent, String title, File xmlFile) throws IOException, SAXException{
		return getDialog(parent, title, VersionInfo.getVersionInfo(xmlFile));
	}
}
