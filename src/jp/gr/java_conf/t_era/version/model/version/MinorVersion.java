package jp.gr.java_conf.t_era.version.model.version;

/**
 * �}�C�i�[�o�[�W����
 * @author y-terada
 *
 */
public class MinorVersion extends AbstractVersionNumber{
	/**
	 * ���X�g�Ƀ��r�W��������ǉ����܂��B
	 * @param arg ���r�W����
	 */
	public void addRevision(Object arg){
		childs.add((Revision)arg);
	}
}
