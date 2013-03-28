package jp.gr.java_conf.t_era.version.model.version;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.betwixt.io.BeanReader;
import org.xml.sax.SAXException;


/**
 * バージョン情報
 * @author y-terada
 *
 */
public class VersionInfo {

	private Application application;

	/**
	 * メジャーバージョンのリスト
	 */
	private List<AbstractVersionNumber> childs = new ArrayList<AbstractVersionNumber>();

	public String getAppName() {
		if (application == null) {
			return null;
		}
		return application.getName();
	}

	public Application getApplication() {
		return this.application;
	}
	public void setApplication(Object application) {
		this.application = (Application)application;
	}

	/**
	 * リストにメジャーバージョンを追加します。
	 * @param arg
	 */
	public void addMajorVersion(Object arg){
		childs.add((MajorVersion)arg);
	}

	/**
	 * メジャーバージョンのリストを返します。
	 * @return 下位バージョンのリスト
	 */
	public List<AbstractVersionNumber> getMajorVersion()	{
		return childs;
	}

	/**
	 * XMLファイルを解析して、バージョン情報を取得します。
	 * <p>
	 * xmlファイルの形式は、ReleaseNote.dtd / ReleaseNote.xsdに定義します。
	 * </p>
	 * @param xmlFile バージョン情報のXMLファイル
	 * @return バージョン情報
	 * @throws IOException
	 * @throws SAXException
	 */
	public static VersionInfo getVersionInfo(File xmlFile) throws IOException, SAXException {
		BeanReader obj = getBeanReader();

		return (VersionInfo)obj.parse(xmlFile);
	}

	public static VersionInfo getVersionInfo(InputStream is) throws IOException, SAXException {
		BeanReader obj = getBeanReader();

		return (VersionInfo)obj.parse(is);
	}

	private static BeanReader getBeanReader() {
		BeanReader obj = new BeanReader();

		obj.push(new VersionInfo());
		obj.addCallMethod("Version/Application", "setApplication", 1);
		obj.addObjectCreate("Version/Application", Application.class);
		obj.addSetProperties("Version/Application");
		obj.addCallParam("Version/Application", 0, 0);

		obj.addCallMethod("Version/Major", "addMajorVersion", 1);
		obj.addObjectCreate("Version/Major", MajorVersion.class);
		obj.addSetProperties("Version/Major");
		obj.addCallParam("Version/Major", 0, 0);

		obj.addCallMethod("Version/Major/Minor", "addMinorVersion", 1);
		obj.addObjectCreate("Version/Major/Minor", MinorVersion.class);
		obj.addSetProperties("Version/Major/Minor");
		obj.addCallParam("Version/Major/Minor", 0, 0);

		obj.addCallMethod("Version/Major/Minor/Revision", "addRevision", 1);
		obj.addObjectCreate("Version/Major/Minor/Revision", Revision.class);
		obj.addSetProperties("Version/Major/Minor/Revision");
		obj.addCallParam("Version/Major/Minor/Revision", 0, 0);

		obj.addCallMethod("Version/Major/Minor/Revision/*", "addChild", 1);
		obj.addObjectCreate("Version/Major/Minor/Revision/*", Revision.class);
		obj.addSetProperties("Version/Major/Minor/Revision/*");
		obj.addCallParam("Version/Major/Minor/Revision/*", 0, 0);

		return obj;
	}
}
