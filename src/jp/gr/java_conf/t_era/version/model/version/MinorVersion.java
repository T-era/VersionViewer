package jp.gr.java_conf.t_era.version.model.version;

/**
 * マイナーバージョン
 * @author y-terada
 *
 */
public class MinorVersion extends AbstractVersionNumber{
	/**
	 * リストにリビジョン情報を追加します。
	 * @param arg リビジョン
	 */
	public void addRevision(Object arg){
		childs.add((Revision)arg);
	}
}
