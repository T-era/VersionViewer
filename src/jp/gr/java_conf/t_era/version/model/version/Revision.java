package jp.gr.java_conf.t_era.version.model.version;

/**
 * ���r�W�������
 * @author y-terada
 *
 */
public class Revision extends AbstractVersionNumber{
	/**
	 * �T�u���r�W������ǉ����܂��B
	 * @param arg �T�u���r�W����
	 */
	public void addChild(Object arg){
		childs.add((Revision)arg);
	}
}
