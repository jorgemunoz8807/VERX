package citi.edu.cu.utils;

import java.awt.Image;

import com.glavsoft.viewer.Viewer;
import com.glavsoft.viewer.cli.Parser;
import com.glavsoft.viewer.swing.ParametersHandler;

public class Test {

	public static Viewer viewer;

	/**
	 * Crea una nueva conexion con los parametros de conecion *
	 * 
	 * @param -host=192.168.202.100","-port=5900","-password=a
	 */
	public static void StartViewer(String[] conectionParams) {
		String[] args1 = conectionParams;
		Parser parser = new Parser();
		ParametersHandler.completeParserOptions(parser);
		parser.parse(args1);
		if (parser.isSet(ParametersHandler.ARG_HELP)) {
			Viewer.printUsage(parser.optionsUsage());
			System.exit(0);
		}
		viewer = new Viewer(parser);
		viewer.run();
		viewer.setVisible(false);
	}

	public static Viewer getViewer() {
		return viewer;
	}

	public static void setViewer(Viewer viewer) {
		Test.viewer = viewer;
	}

	// ------------------------------------------------------
	/**
 * 
 */
	public static Image getImage() {
		return viewer.getSurface().getRenderer().getOffscreenImage();
	}

}
