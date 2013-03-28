package jp.gr.java_conf.t_era.version.view.parts;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.tree.TreeNode;

import jp.gr.java_conf.t_era.version.model.version.AbstractVersionNumber;
import jp.gr.java_conf.t_era.version.model.version.VersionInfo;

/**
 * �o�[�W�������c���[�̃m�[�h
 * @author y-terada
 *
 */
class VersionTreeNode implements TreeNode{
	/**
	 * �e�m�[�h
	 */
	private final VersionTreeNode parent;
	/**
	 * �q�m�[�h�̃��X�g
	 */
	private final List<VersionTreeNode> list;
	/**
	 * �\��������
	 */
	private final String view;
	/**
	 * ���l
	 */
	private final String description;
	/**
	 * �o�[�W�����ԍ�
	 */
	private final String fullNumber;

	/**
	 * �o�[�W���������c���[�m�[�h�ɕϊ����܂��B
	 * @param arg
	 * @return �o�[�W�������̃c���[�m�[�h
	 */
	static  VersionTreeNode toTreeNode(VersionInfo arg){
		return new VersionTreeNode(null, "", "", "Ver", arg.getMajorVersion());
	}

	/**
	 * �o�[�W�������Ɛe�m�[�h�ƁA�c���[�m�[�g�C���X�^���X�𐶐����܂��B
	 * @param parent �e�m�[�h
	 * @param source ��������C���X�^���X���\������o�[�W�������
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
	 * �o�[�W�������̃c���[�m�[�h�𐶐����܂��B
	 * <br>�e�m�[�h�ɂ��āA��������m�[�h�����[�g�m�[�h�̏ꍇ�Anull���w�肵�܂��B
	 * <br>���̑��̈����ɂ��Ă�null�w��͂ł��܂���B�󕶎���܂��͋󃊃X�g�������ɂ��܂��B
	 * @param parent �e�m�[�h
	 * @param view �\�����镶����
	 * @param description ���l
	 * @param fullNumber �o�[�W�����ԍ��\��
	 * @param childs �q�m�[�h�̃��X�g
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
	 * �o�[�W�����ԍ���Ԃ��܂��B
	 * @return �o�[�W�����ԍ�
	 */
	private String getFullNumber(){
		return fullNumber + ".";
	}

	/**
	 * ���l��Ԃ��܂��B
	 * @return ���l
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
