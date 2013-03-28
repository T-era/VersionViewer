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
 * バージョン情報をツリー形式で表示するビュアー
 * @author y-terada
 *
 */
public class VersionTreeViewer extends JTree{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 備考情報がない場合にツールチップに表示する文字列
	 */
	private static final String LABEL_NO_DESCRIPTION = "No info";

	/**
	 * 表示するバージョンツリーを指定して、インスタンス化します。
	 * @param newModel 表示するバージョン情報のルートノード
	 */
	VersionTreeViewer (TreeNode newModel){
		super(newModel);

		MouseMotionListener mml = new MouseMotionAdapter() {
			/**
			 * マウスの動きに合わせてツールチップに表示する文字列を設定します。
			 * (オンマウスのノードが持つ備考情報がツールチップの表示内容です。)
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
	 * ツールチップに表示する
	 * 指定されたパスのノードが持つ備考情報が表示内容です。
	 * @param path ノードの指定
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
