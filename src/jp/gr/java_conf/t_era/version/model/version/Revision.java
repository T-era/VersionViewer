package jp.gr.java_conf.t_era.version.model.version;

/**
 * リビジョン情報
 * @author y-terada
 *
 */
public class Revision extends AbstractVersionNumber{
	/**
	 * サブリビジョンを追加します。
	 * @param arg サブリビジョン
	 */
	public void addChild(Object arg){
		childs.add((Revision)arg);
	}
}
