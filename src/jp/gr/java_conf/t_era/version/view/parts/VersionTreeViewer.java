package jp.gr.java_conf.t_era.version.view.parts;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.xml.sax.SAXException;

import jp.gr.java_conf.t_era.version.model.version.VersionInfo;

/**
 * �o�[�W���������c���[�`���ŕ\������r���A�[
 * @author y-terada
 *
 */
public class VersionTreeViewer extends JTree{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ���l��񂪂Ȃ��ꍇ�Ƀc�[���`�b�v�ɕ\�����镶����
	 */
	private static final String LABEL_NO_DESCRIPTION = "No info";

	/**
	 * �\������o�[�W�����c���[���w�肵�āA�C���X�^���X�����܂��B
	 * @param newModel �\������o�[�W�������̃��[�g�m�[�h
	 */
	VersionTreeViewer (TreeNode newModel){
		super(newModel);

		MouseMotionListener mml = new MouseMotionAdapter() {
			/**
			 * �}�E�X�̓����ɍ��킹�ăc�[���`�b�v�ɕ\�����镶�����ݒ肵�܂��B
			 * (�I���}�E�X�̃m�[�h�������l��񂪃c�[���`�b�v�̕\�����e�ł��B)
			 */
			@Override
			public void mouseMoved(MouseEvent e) {
				TreePath selPath = VersionTreeViewer.this.getPathForLocation(e.getX(), e.getY());

				setToolTipText(selPath);
			}
		};
		this.addMouseMotionListener(mml);
	}

	/**
	 * �c�[���`�b�v�ɕ\������
	 * �w�肳�ꂽ�p�X�̃m�[�h�������l��񂪕\�����e�ł��B
	 * @param path �m�[�h�̎w��
	 */
	private void setToolTipText(TreePath path){
		if (path != null && path.getLastPathComponent() instanceof VersionTreeNode) {
			VersionTreeNode node = (VersionTreeNode)path.getLastPathComponent();
			if (node.getDescription() == null || node.getDescription().equals("")) {
				this.setToolTipText(LABEL_NO_DESCRIPTION);
			} else {
				this.setToolTipText(node.getDescription());
			}
		} else {
			this.setToolTipText("");
		}
	}

	public static VersionTreeViewer getTreeViewer(File xmlFile) throws IOException, SAXException{
		VersionInfo info = VersionInfo.getVersionInfo(xmlFile);
		return getTreeViewer(info);
	}
//	public static VersionTreeViewer getTreeViewer(String classPathLocation) throws IOException, SAXException{
//		VersionInfo info = VersionInfo.getVersionInfo(classPathLocation);
//		return getTreeViewer(info);
//	}
	public static VersionTreeViewer getTreeViewer(VersionInfo info) {
		TreeNode root = VersionTreeNode.toTreeNode(info);
		return new VersionTreeViewer(root);
	}
}
