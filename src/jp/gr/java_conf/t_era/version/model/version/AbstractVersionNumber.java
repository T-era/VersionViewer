package jp.gr.java_conf.t_era.version.model.version;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * バージョン番号<br>
 * バージョンを階層構造として表現します。
 * この抽象クラスを実装する場合、階層構造として下位となるバージョンを追加するメソッドを定義するべきです。
 * @author y-terada
 *
 */
public abstract class AbstractVersionNumber{
	/**
	 * バージョン番号
	 */
	private String number = "";
	/**
	 * バージョン情報として持つ文字列情報
	 */
	private String note = "";
	/**
	 * 備考
	 */
	private String description = "";

	/**
	 * 下位バージョン情報のリスト
	 */
	protected List<AbstractVersionNumber> childs;

	protected AbstractVersionNumber(){
		childs = new ArrayList<AbstractVersionNumber>();
	}

	/**
	 * バージョン番号を返します。
	 * @return バージョン番号
	 */
	public String getNumber(){
		return number;
	}
	/**
	 * バージョン情報を返します。
	 * @return バージョン情報
	 */
	public String getNote(){
		return note;
	}
	/**
	 * 備考を返します。
	 * @return 備考
	 */
	public String getDescription(){
		return description;
	}

	/**
	 * 階層構造上、下位となるバージョンの一覧を返します。
	 * @return 下位となるバージョンの一覧
	 */
	public List<AbstractVersionNumber> getChilds(){
		return childs;
	}

	/**
	 * バージョン番号を設定します。
	 * <br>XMLパーサのために、public に公開されています。
	 * @param arg
	 */
	@Deprecated
	public void setNumber(String arg){
		this.number = arg;
	}

	/**
	 * バージョン情報として持つ文字列情報を設定します。
	 * <br>XMLパーサのために、public に公開されています。
	 * @param arg
	 */
	@Deprecated
	public void setNote(String arg){
		this.note = arg;
	}
	/**
	 * バージョン情報として持つ備考情報を設定します。
	 * <br>XMLパーサのために、public に公開されています。
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
