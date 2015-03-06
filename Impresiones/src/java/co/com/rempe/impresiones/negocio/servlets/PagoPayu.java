/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.servlets;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.constantes.EDireccion;
import co.com.rempe.impresiones.negocio.delegado.PayuDelegado;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import com.google.gson.Gson;
import com.payu.sdk.PayU;
import com.payu.sdk.PayUPayments;
import com.payu.sdk.exceptions.ConnectionException;
import com.payu.sdk.exceptions.InvalidParametersException;
import com.payu.sdk.exceptions.PayUException;
import com.payu.sdk.model.Currency;
import com.payu.sdk.model.PaymentCountry;
import com.payu.sdk.model.PaymentMethod;
import com.payu.sdk.model.TransactionResponse;
import com.payu.sdk.utils.LoggerUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "PagoPayu", urlPatterns = {"/pagarConPayu", "/conectarPayu"})
public class PagoPayu extends HttpServlet {

    public PayuDelegado payuDelegado;

    @Override
    public void init() throws ServletException {
        super.init();
        payuDelegado = PayuDelegado.getInstancia();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Respuesta respuesta = new Respuesta();
        try {
            String url = request.getServletPath();
            EDireccion direccion = EDireccion.getDireccion(url);
            switch (direccion) {
                case PAGO_PAYU:
                    respuesta = pagarConPayu(request, response);
                    break;
                case CONECTAR_PAYU:
                    respuesta = conectarPayu(request);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            String json = new Gson().toJson(respuesta);
            out.print(json);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Respuesta pagarConPayu(HttpServletRequest request, HttpServletResponse response1) {
        Respuesta respuesta = new Respuesta();
        try {
            String reference = "payment_test_00000001";
            String value = "10000";

            Map<String, String> parameters = new HashMap<String, String>();

//Ingrese aquí el identificador de la cuenta.
            parameters.put(PayU.PARAMETERS.ACCOUNT_ID, "500538");
//Ingrese aquí el código de referencia.
            parameters.put(PayU.PARAMETERS.REFERENCE_CODE, "" + reference);
//Ingrese aquí la descripción.
            parameters.put(PayU.PARAMETERS.DESCRIPTION, "payment test");
//Ingrese aquí el idima de la orden.
            parameters.put(PayU.PARAMETERS.LANGUAGE, "Language.es");

// -- Valores --
//Ingrese aquí el valor.
            parameters.put(PayU.PARAMETERS.VALUE, "" + value);
//Ingrese aquí la moneda.
            parameters.put(PayU.PARAMETERS.CURRENCY, "" + Currency.COP.name());

// -- Comprador --
//Ingrese aquí el id del comprador.
            parameters.put(PayU.PARAMETERS.BUYER_ID, "1");
//Ingrese aquí el nombre del comprador.
            parameters.put(PayU.PARAMETERS.BUYER_NAME, "First name and second buyer  name");
//Ingrese aquí el email del comprador.
            parameters.put(PayU.PARAMETERS.BUYER_EMAIL, "buyer_test@test.com");
//Ingrese aquí el teléfono de contacto del comprador.
            parameters.put(PayU.PARAMETERS.BUYER_CONTACT_PHONE, "7563126");
//Ingrese aquí el documento de contacto del comprador.
            parameters.put(PayU.PARAMETERS.BUYER_DNI, "5415668464654");
//Ingrese aquí la dirección del comprador.
            parameters.put(PayU.PARAMETERS.BUYER_STREET, "calle 100");
            parameters.put(PayU.PARAMETERS.BUYER_STREET_2, "5555487");
            parameters.put(PayU.PARAMETERS.BUYER_CITY, "Medellin");
            parameters.put(PayU.PARAMETERS.BUYER_STATE, "Antioquia");
            parameters.put(PayU.PARAMETERS.BUYER_COUNTRY, "CO");
            parameters.put(PayU.PARAMETERS.BUYER_POSTAL_CODE, "000000");
            parameters.put(PayU.PARAMETERS.BUYER_PHONE, "7563126");

// -- Pagador --
//Ingrese aquí el id del pagador.
            parameters.put(PayU.PARAMETERS.PAYER_ID, "1");
//Ingrese aquí el nombre del pagador.
            parameters.put(PayU.PARAMETERS.PAYER_NAME, "First name and second payer name");
//Ingrese aquí el email del pagador.
            parameters.put(PayU.PARAMETERS.PAYER_EMAIL, "payer_test@test.com");
//Ingrese aquí el teléfono de contacto del pagador.
            parameters.put(PayU.PARAMETERS.PAYER_CONTACT_PHONE, "7563126");
//Ingrese aquí el documento de contacto del pagador.
            parameters.put(PayU.PARAMETERS.PAYER_DNI, "5415668464654");
//Ingrese aquí la dirección del pagador.
            parameters.put(PayU.PARAMETERS.PAYER_STREET, "calle 93");
            parameters.put(PayU.PARAMETERS.PAYER_STREET_2, "125544");
            parameters.put(PayU.PARAMETERS.PAYER_CITY, "Bogota");
            parameters.put(PayU.PARAMETERS.PAYER_STATE, "Bogota");
            parameters.put(PayU.PARAMETERS.PAYER_COUNTRY, "CO");
            parameters.put(PayU.PARAMETERS.PAYER_POSTAL_CODE, "000000");
            parameters.put(PayU.PARAMETERS.PAYER_PHONE, "7563126");

// -- Datos de la tarjeta de crédito -- 
//Ingrese aquí el número de la tarjeta de crédito
            parameters.put(PayU.PARAMETERS.CREDIT_CARD_NUMBER, "4097440000000004");
//Ingrese aquí la fecha de vencimiento de la tarjeta de crédito
            parameters.put(PayU.PARAMETERS.CREDIT_CARD_EXPIRATION_DATE, "2014/12");
//Ingrese aquí el código de seguridad de la tarjeta de crédito
            parameters.put(PayU.PARAMETERS.CREDIT_CARD_SECURITY_CODE, "321");
//Ingrese aquí el nombre de la tarjeta de crédito
            parameters.put(PayU.PARAMETERS.PAYMENT_METHOD, PaymentMethod.VISA.name());

//Ingrese aquí el número de cuotas.
            parameters.put(PayU.PARAMETERS.INSTALLMENTS_NUMBER, "1");
//Ingrese aquí el nombre del pais.
            parameters.put(PayU.PARAMETERS.COUNTRY, PaymentCountry.CO.name());

//Session id del device.
            parameters.put(PayU.PARAMETERS.DEVICE_SESSION_ID, "vghs6tvkcle931686k1900o6e1");
//IP del pagadador
            parameters.put(PayU.PARAMETERS.IP_ADDRESS, "127.0.0.1");
//Cookie de la sesión actual.
            parameters.put(PayU.PARAMETERS.COOKIE, "pt1t38347bs6jc9ruv2ecpv7o2");
//Cookie de la sesión actual.
            parameters.put(PayU.PARAMETERS.USER_AGENT, "Mozilla/5.0 (Windows NT 5.1; rv:18.0) Gecko/20100101 Firefox/18.0");

//Solicitud de autorización y captura
            TransactionResponse response = PayUPayments.doAuthorizationAndCapture(parameters);

//Respuesta
            if (response != null) {
                response.getOrderId();
                response.getTransactionId();
                response.getState();
                if (response.getState().toString().equalsIgnoreCase("PENDING")) {
                    response.getPendingReason();
                }
                response.getPaymentNetworkResponseCode();
                response.getPaymentNetworkResponseErrorMessage();
                response.getTrazabilityCode();
                response.getResponseCode();
                response.getResponseMessage();
            }
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setDatos(response);
            respuesta.setMensaje("Transacción realizada.");
        } catch (Throwable t) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("No se pudo realizar la tranzacción " + t.getMessage());
        }
        return respuesta;
    }

    private Respuesta conectarPayu(HttpServletRequest request) {
        Respuesta respuesta = new Respuesta();
        try {
            boolean response = (PayUPayments.doPing());
            LoggerUtil.info("{0}", response);
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setDatos(response);
            respuesta.setMensaje("Se ha consultado la conectividad con payu.");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar la conectividad con payu."+e.getMessage());
        }
        return respuesta;
    }
}
