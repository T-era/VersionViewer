package jp.gr.java_conf.t_era.version.model.version;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * �o�[�W�����ԍ�<br>
 * �o�[�W�������K�w�\���Ƃ��ĕ\�����܂��B
 * ���̒��ۃN���X����������ꍇ�A�K�w�\���Ƃ��ĉ��ʂƂȂ�o�[�W������ǉ����郁�\�b�h���`����ׂ��ł��B
 * @author y-terada
 *
 */
public abstract class AbstractVersionNumber{
	/**
	 * �o�[�W�����ԍ�
	 */
	private String number = "";
	/**
	 * �o�[�W�������Ƃ��Ď���������
	 */
	private String note = "";
	/**
	 * ���l
	 */
	private String description = "";

	/**
	 * ���ʃo�[�W�������̃��X�g
	 */
	protected List<AbstractVersionNumber> childs;

	protected AbstractVersionNumber(){
		childs = new ArrayList<AbstractVersionNumber>();
	}

	/**
	 * �o�[�W�����ԍ���Ԃ��܂��B
	 * @return �o�[�W�����ԍ�
	 */
	public String getNumber(){
		return number;
	}
	/**
	 * �o�[�W��������Ԃ��܂��B
	 * @return �o�[�W�������
	 */
	public String getNote(){
		return note;
	}
	/**
	 * ���l��Ԃ��܂��B
	 * @return ���l
	 */
	public String getDescription(){
		return description;
	}

	/**
	 * �K�w�\����A���ʂƂȂ�o�[�W�����̈ꗗ��Ԃ��܂��B
	 * @return ���ʂƂȂ�o�[�W�����̈ꗗ
	 */
	public List<AbstractVersionNumber> getChilds(){
		return childs;
	}

	/**
	 * �o�[�W�����ԍ���ݒ肵�܂��B
	 * <br>XML�p�[�T�̂��߂ɁApublic �Ɍ��J����Ă��܂��B
	 * @param arg
	 */
	@Deprecated
	public void setNumber(String arg){
		this.number = arg;
	}

	/**
	 * �o�[�W�������Ƃ��Ď����������ݒ肵�܂��B
	 * <br>XML�p�[�T�̂��߂ɁApublic �Ɍ��J����Ă��܂��B
	 * @param arg
	 */
	@Deprecated
	public void setNote(String arg){
		this.note = arg;
	}
	/**
	 * �o�[�W�������Ƃ��Ď����l����ݒ肵�܂��B
	 * <br>XML�p�[�T�̂��߂ɁApublic �Ɍ��J����Ă��܂��B
	 * @param arg
	 */
	@Deprecated
	public void setDescription(String arg){
		this.description = arg;
	}

	/**
	 * For debug only.
	 */
	public String toString(){
		return MessageFormat.format("{0}:{1}({2})", new Object[]{number, note, description});
	}
}
