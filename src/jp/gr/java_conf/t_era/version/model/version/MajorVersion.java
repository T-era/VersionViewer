package jp.gr.java_conf.t_era.version.model.version;

/**
 * ���W���[�o�[�W����
 * @author y-terada
 *
 */
public class MajorVersion extends AbstractVersionNumber{
	/**
	 * ���X�g�Ƀ}�C�i�[�o�[�W������ǉ����܂��B
	 * @param arg �}�C�i�[�o�[�W����
	 */
	public void addMinorVersion(Object arg){
		childs.add((MinorVersion)arg);
	}
}
