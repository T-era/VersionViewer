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
 * ���@�[�W��������\������_�C�A���O���쐬����t�@�N�g��
 * @author y-terada
 *
 */
public class VersionDialog{
	/**
	 * �w�肵���o�[�W��������\������_�C�A���O�𐶐����A�Ԃ��܂��B
	 * @param parent �_�C�A���O�̐e�t���[��
	 * @param title �_�C�A���O�^�C�g��
	 * @param version �o�[�W�������
	 * @return �_�C�A���O�C���X�^���X
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
	 * �w�肵���t�@�C������o�[�W��������ǂݏo���A�\������_�C�A���O�𐶐����A�Ԃ��܂��B
	 * @param parent �_�C�A���O�̐e�t���[��
	 * @param title �_�C�A���O�^�C�g��
	 * @param xmlFile �o�[�W���������L�ڂ����t�@�C��
	 * @return �_�C�A���O�C���X�^���X
	 */
	public static JDialog getDialog(JFrame parent, String title, File xmlFile) throws IOException, SAXException{
		return getDialog(parent, title, VersionInfo.getVersionInfo(xmlFile));
	}
}
