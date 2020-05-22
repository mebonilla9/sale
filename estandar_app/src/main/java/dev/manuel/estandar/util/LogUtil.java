package dev.manuel.estandar.util;


import dev.manuel.estandar.excepcion.AplicacionException;

/**
 *
 * @author lrey
 */
public class LogUtil {

    public static void error(Throwable e) {
        e.printStackTrace();
    }

    public static void error(Throwable e, String mensaje) {
        System.err.println("" + mensaje);
        e.printStackTrace();
    }

    public static void error(AplicacionException ex) {
        if (ex.getCodigo() >= 0) {
            System.out.println(ex.getMensaje());
            return;
        }
        ex.printStackTrace();
    }

    public static void info(String mensaje) {
        System.out.println(mensaje);
    }

    public static void infoError(Object obj) {
        System.err.println(obj);
    }
}
