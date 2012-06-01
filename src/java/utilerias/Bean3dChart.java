/**
 *
 * UTILERIA PARA TRAZAR LA GRAFICA EN CLIENTE GOL
 */
package utilerias;

import de.laures.cewolf.DatasetProducer;
import java.io.Serializable;

import java.util.Date;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * 
 * @author Rodrigo
 */
public class Bean3dChart implements DatasetProducer , Serializable  {

	private static final long serialVersionUID = 1L;
	private double dato1 = 0;
	private double dato2 = 0;
	private double dato3 = 0;
	private double dato4 = 0;

	private static final Log log;
	static {
		log = LogFactory.getLog(Bean3dChart.class);
	}

	public Bean3dChart(double dato1, double dato2, double dato3, double dato4,
			String strEtiqueta) {
		this.dato1 = dato1;
		this.dato2 = dato2;
		this.dato3 = dato3;
		this.dato4 = dato4;
	}

	public Object produceDataset(Map params) {

		if (dato1 < 0) {
			if (dato1 < -150.0)
				dato1 = 150.0;
		} else {
			if (dato1 > 150.0)
				dato1 = 150.0;
		}

		if (dato2 < 0) {
			if (dato2 < -150.0)
				dato2 = 150.0;
		} else {
			if (dato2 > 150.0)
				dato2 = 150.0;
		}

		if (dato3 < 0) {
			if (dato3 < -150.0)
				dato3 = 150.0;
		} else {
			if (dato3 > 150.0)
				dato3 = 150.0;
		}

		if (dato4 < 0) {
			if (dato4 < -150.0)
				dato4 = 150.0;
		} else {
			if (dato4 > 150.0)
				dato4 = 150.0;
		}

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 1; i <= 1; i++) {
			dataset.addValue(dato1, "Comportamiento de Pago", new Double(1));
			dataset.addValue(dato2, "Género y Clase", new Double(2));
			dataset.addValue(dato3, "Arraigo", new Double(3));
			dataset.addValue(dato4, "Aspectos Diferenciadores", new Double(4));
		}

		return dataset;

	}

	public String getProducerId() {
		return "categoryData";
	}

	public boolean hasExpired(Map params, Date since) {
		log.debug(this + ".hasExpired()");
		return System.currentTimeMillis() - since.getTime() > 1000L;// false;
	}


}
