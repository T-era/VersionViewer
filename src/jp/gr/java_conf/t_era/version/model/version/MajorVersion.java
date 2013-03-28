package jp.gr.java_conf.t_era.version.model.version;

/**
 * メジャーバージョン
 * @author y-terada
 *
 */
public class MajorVersion extends AbstractVersionNumber{
	/**
	 * リストにマイナーバージョンを追加します。
	 * @param arg マイナーバージョン
	 */
	public void addMinorVersion(Object arg){
		childs.add((MinorVersion)arg);
	}
}
