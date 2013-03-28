package jp.gr.java_conf.t_era.version.view.parts;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.tree.TreeNode;

import jp.gr.java_conf.t_era.version.model.version.AbstractVersionNumber;
import jp.gr.java_conf.t_era.version.model.version.VersionInfo;

/**
 * バージョン情報ツリーのノード
 * @author y-terada
 *
 */
class VersionTreeNode implements TreeNode{
	/**
	 * 親ノード
	 */
	private final VersionTreeNode parent;
	/**
	 * 子ノードのリスト
	 */
	private final List<VersionTreeNode> list;
	/**
	 * 表示文字列
	 */
	private final String view;
	/**
	 * 備考
	 */
	private final String description;
	/**
	 * バージョン番号
	 */
	private final String fullNumber;

	/**
	 * バージョン情報をツリーノードに変換します。
	 * @param arg
	 * @return バージョン情報のツリーノード
	 */
	static  VersionTreeNode toTreeNode(VersionInfo arg){
		return new VersionTreeNode(null, "", "", "Ver", arg.getMajorVersion());
	}

	/**
	 * バージョン情報と親ノードと、ツリーノートインスタンスを生成します。
	 * @param parent 親ノード
	 * @param source 生成するインスタンスが表現するバージョン情報
	 */
	private VersionTreeNode(VersionTreeNode parent, AbstractVersionNumber source){
		this(
				  parent
				, source.getNote()
				, source.getDescription()
				, parent.getFullNumber() + source.getNumber()
				, source.getChilds());
	}

	/**
	 * バージョン情報のツリーノードを生成します。
	 * <br>親ノードについて、生成するノードがルートノードの場合、nullを指定します。
	 * <br>その他の引数についてはnull指定はできません。空文字列または空リストを引数にします。
	 * @param parent 親ノード
	 * @param view 表示する文字列
	 * @param description 備考
	 * @param fullNumber バージョン番号表示
	 * @param childs 子ノードのリスト
	 */
	private VersionTreeNode(VersionTreeNode parent, String view, String description, String fullNumber, List<AbstractVersionNumber> childs) {
		this.parent = parent;
		this.view = view;
		this.description = description;
		this.fullNumber = fullNumber;

		this.list = new ArrayList<VersionTreeNode>();
		for (AbstractVersionNumber child: childs) {
			list.add(new VersionTreeNode(this, child));
		}
	}

	/**
	 * バージョン番号を返します。
	 * @return バージョン番号
	 */
	private String getFullNumber(){
		return fullNumber + ".";
	}

	/**
	 * 備考を返します。
	 * @return 備考
	 */
	String getDescription(){
		return description;
	}

	@Override
	public String toString(){
		return getFullNumber() + view;
	}
	@Override
	public Enumeration<VersionTreeNode> children() {
		return new Enumeration<VersionTreeNode>(){
			private final Iterator<VersionTreeNode> ite = list.iterator();

			@Override
			public boolean hasMoreElements() {
				return ite.hasNext();
			}
			@Override
			public VersionTreeNode nextElement() {
				return ite.next();
			}
		};
	}

	@Override
	public boolean getAllowsChildren() {
		return list.size() != 0;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return list.get(childIndex);
	}

	@Override
	public int getIndex(TreeNode node) {
		return list.indexOf(node);
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		return list.size() == 0;
	}

	@Override
	public int getChildCount() {
		return list.size();
	}
}
