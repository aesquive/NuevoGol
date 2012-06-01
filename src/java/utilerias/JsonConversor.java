package utilerias;


import java.util.List;


/**
 *
 * @author Ruben G.g
 */
public class JsonConversor {


    public static String toJson(List o){
        throw new IllegalAccessError("Aun no imp");
    }

    /**
     * Parejas de valores [llave1:val1, ..., llaven:valn]
     * @param o
     * @return
     */
    public static String toJson(String[] jsonArr){

        StringBuilder json = new StringBuilder();
        json.append("{");

        for(String reg: jsonArr){

            String llave = reg.split(":")[0];
            String val = reg.split(":")[1];
            String jval = "\"" + llave + "\"" + " : " + "\"" + val + "\"";
            json.append(jval);
        }
        return json.append("}").toString();
    }

    /**
     * llaves[], valores[]
     * @param llaves
     * @param valores
     * @return
     */
    public static String toJson(Object[] llaves, Object[] valores){

        StringBuilder json = new StringBuilder();
        json.append("{");

        for (int i = 0; i < valores.length; i++) {

            json.append(json.length() > 1? ",": "");
            String reg = "\"" + llaves[i] + "\" : " + "\"" + valores[i] + "\" " ;
            json.append(reg);
        }
        return json.toString() + "}";
    }

    public static void main(String[] args) {

        String[] llaves = {"llave1", "llave2", "llave3"};
        String[] vals = {"val1", "val2", "val3"};

        String json = JsonConversor.toJson(llaves, vals);
        System.out.println(json);
    }

}
