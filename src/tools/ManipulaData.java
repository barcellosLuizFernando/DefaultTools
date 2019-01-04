/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Luiz Fernando Dill Barcellos
 */
public class ManipulaData {

    public static final DateFormat DATEOUT = new SimpleDateFormat("yyyy/MM/dd");
    public static final DateFormat DATEIN = new SimpleDateFormat("dd/MM/yyyy");

    public static final int MILLISECONDS = 0;
    public static final int SECONDS = 1;
    public static final int MINUTES = 2;
    public static final int HOURS = 3;
    public static final int DAYS = 4;
    public static final int MONTHS = 5;
    public static final int YEARS = 6;

    public static Date data;

    /**
     * Converte uma competência em data. Recebe uma string do tipo "DD/AAAA" e
     * retorna a data do último dia do mês, sem formatação.
     *
     * @param competencia String do tipo "DD/AAAA"
     * @return Data sem formatação
     * @throws java.lang.Exception
     */
    public static Date lastDay(String competencia) throws Exception {
        try {
            data = DATEIN.parse("01/" + competencia);

            Calendar data2 = Calendar.getInstance();
            data2.setTime(data);

            data2.set(Calendar.MONTH, data2.get(Calendar.MONTH) + 1);
            data2.set(Calendar.DAY_OF_MONTH, data2.get(Calendar.DAY_OF_MONTH) - 1);

            data = data2.getTime();

            System.out.println("Formatação: " + data);

        } catch (ParseException ex) {
            throw new Exception("Não foi possível definir o último dia da competência " + competencia + ":\n" + ex);
        }

        return data;
    }

    public static String lastDayToBR(String competencia) throws Exception {
        try {
            data = DATEIN.parse("01/" + competencia);

            Calendar data2 = Calendar.getInstance();
            data2.setTime(data);

            data2.set(Calendar.MONTH, data2.get(Calendar.MONTH) + 1);
            data2.set(Calendar.DAY_OF_MONTH, data2.get(Calendar.DAY_OF_MONTH) - 1);

            data = data2.getTime();

            System.out.println("Formatação: " + data);

        } catch (ParseException ex) {
            throw new Exception("Não foi possível definir o último dia da competência " + competencia + ":\n" + ex);
        }

        return DATEIN.format(data);
    }

    /**
     * Encontra o último dia de 'N' meses a frente.
     *
     * @param competence
     * @param months
     * @return
     * @throws java.lang.Exception
     */
    public static Date nextMonth(String competence, int months) throws Exception {
        try {

            if (months < 1) {
                throw new Exception("Quantidade de meses precisa ser maior do que zero.\nQuantidade informada:" + months);
            }

            System.out.println("Número de meses para avançar: " + months);

            data = DATEIN.parse("01/" + competence);

            Calendar data2 = Calendar.getInstance();
            data2.setTime(data);

            data2.set(Calendar.MONTH, data2.get(Calendar.MONTH) + (months));
            data2.set(Calendar.DAY_OF_MONTH, data2.get(Calendar.DAY_OF_MONTH) - 1);

            data = data2.getTime();

            System.out.println("Formatação: " + data);

        } catch (ParseException ex) {
            throw new Exception("Não foi possível definir a data futura para a competência " + competence + ":\n" + ex);
        }

        return data;
    }

    /**
     *
     * @param competence
     * @return
     * @throws Exception
     */
    public static Date nextMonth(String competence) throws Exception {

        int months = timeLapse(firstDay(competence), new Date(), MONTHS).intValue();

        nextMonth(competence, months);

        return data;
    }

    /**
     * Define quantidade de meses entre duas datas.
     *
     * @param x Menor data;
     * @param y Maior data;
     * @param period
     * @return
     * @throws java.lang.Exception
     */
    public static Double timeLapse(Date x, Date y, int period) throws Exception {

        if (period > 6 | period < 0) {
            throw new Exception("Período requisitado é inválido.");
        }

        System.out.println("Data 1: " + x);
        System.out.println("Data 2: " + y);

        Long milliseconds = (y.getTime() - x.getTime() + 86400000L);
        Double seconds = milliseconds.doubleValue() / 1000.00;
        Double minutes = seconds / 60.00;
        Double hours = minutes / 60.00;
        Double days = hours / 24.00;
        Double months = days / 30.00;
        Double years = months / 12.00;

        Double timeLapse;

        switch (period) {
            case MILLISECONDS:
                timeLapse = milliseconds.doubleValue();
                break;
            case SECONDS:
                timeLapse = seconds;
                break;
            case MINUTES:
                timeLapse = minutes;
                break;
            case HOURS:
                timeLapse = hours;
                break;
            case DAYS:
                timeLapse = days;
                break;
            case MONTHS:
                timeLapse = months;
                break;
            default:
                timeLapse = years;
                break;
        }

        System.out.println("Período: " + timeLapse);

        return timeLapse;
    }

    /**
     * Recebe uma data e extrai o mês e o ano no formado MM/yyyy.
     *
     * @param date
     * @return
     * @throws java.lang.Exception
     */
    public static String getCompetenceBR(Date date) throws Exception {

        System.out.println("Data recebida para extrair competência: " + date);
        String x = "";

        try {
            x = (date.getMonth() + 1) + "/" + (date.getYear() + 1900);
            if (x.length() < 7) {
                x = "0" + x;
            }

        } catch (Exception e) {
            throw new Exception("Não foi possível extrair a competência da data informada:\n" + date + ", " + e);
        }

        System.out.println("Competência extraída: " + x);

        return x;

    }

    /**
     * Retorna o último dia útil da competência informada
     *
     * @param competencia
     * @return
     * @throws java.lang.Exception
     */
    public static Date ultimoDiaUtil(String competencia) throws Exception {
        try {
            data = DATEIN.parse("01/" + competencia);

            Calendar data2 = Calendar.getInstance();
            data2.setTime(data);

            data2.set(Calendar.MONTH, data2.get(Calendar.MONTH) + 1);
            data2.set(Calendar.DAY_OF_MONTH, data2.get(Calendar.DAY_OF_MONTH) - 1);

            data = data2.getTime();
            System.out.println("Formatação: " + DATEIN.format(data));
            System.out.println("Dia da Semana: " + data.getDay());

            while (data.getDay() == 0 | data.getDay() == 6) {
                data2.set(Calendar.DAY_OF_MONTH, data2.get(Calendar.DAY_OF_MONTH) - 1);
                data = data2.getTime();

                System.out.println("Formatação: " + DATEIN.format(data));
                System.out.println("Dia da Semana: " + data.getDay());

            }

            System.out.println("Formatação: " + data);

        } catch (ParseException ex) {
            throw new Exception("Não foi possível definir o último dia útil para a competência " + competencia + ".\n" + ex);
        }

        return data;
    }

    /**
     * Verifica qual é o último dia útil do mês posterior.
     *
     * @param competencia
     * @return
     * @throws java.lang.Exception
     */
    public static Date ultimoDiaUtilMesPosterior(String competencia) throws Exception {
        try {
            data = DATEIN.parse("01/" + competencia);

            Calendar data2 = Calendar.getInstance();
            data2.setTime(data);

            data2.set(Calendar.MONTH, data2.get(Calendar.MONTH) + 2);
            data2.set(Calendar.DAY_OF_MONTH, data2.get(Calendar.DAY_OF_MONTH) - 1);

            data = data2.getTime();
            System.out.println("Formatação: " + DATEIN.format(data));
            System.out.println("Dia da Semana: " + data.getDay());

            while (data.getDay() == 0 | data.getDay() == 6) {
                data2.set(Calendar.DAY_OF_MONTH, data2.get(Calendar.DAY_OF_MONTH) - 1);
                data = data2.getTime();

                System.out.println("Formatação: " + DATEIN.format(data));
                System.out.println("Dia da Semana: " + data.getDay());

            }

            System.out.println("Formatação: " + data);

        } catch (ParseException ex) {
            throw new Exception("Não foi possível definir o último dia útil do mês posterior para a competência " + competencia + ".\n" + ex);
        }

        return data;
    }

    /**
     * Verifica qual é o último dia do Mês anterior.
     *
     * @param competencia
     * @return
     * @throws java.lang.Exception
     */
    public static Date ultimoDiaMesAnterior(String competencia) throws Exception {
        try {
            data = DATEIN.parse("01/" + competencia);

            Calendar data2 = Calendar.getInstance();
            data2.setTime(data);

            //data2.set(Calendar.MONTH, data2.get(Calendar.MONTH) + 1);
            data2.set(Calendar.DAY_OF_MONTH, data2.get(Calendar.DAY_OF_MONTH) - 1);

            data = data2.getTime();

            System.out.println("Formatação: " + data);

        } catch (ParseException ex) {
            throw new Exception("Não foi possível definir o último dia do mês anterior para a competência " + competencia + ".\n" + ex);
        }

        return data;
    }

    public static Date firstDay(String competencia) throws Exception {
        try {
            data = DATEIN.parse("01/" + competencia);
            System.out.println("Formatação: " + data);
            return data;

        } catch (ParseException e) {
            throw new Exception("Não foi possível definir o primeiro dia para a competência " + competencia + ".\n" + e);
        }
    }

    public static Date parseBrToDate(String x) throws ParseException {

        return DATEIN.parse(x);

    }

    public static Date parseUsToDate(String x) throws ParseException {

        return DATEIN.parse(x);

    }

    public static int getMonth(String competencia) throws Exception {
        int x = 0;

        try {
            data = DATEIN.parse("01/" + competencia);

            Calendar data2 = Calendar.getInstance();
            data2.setTime(data);

            x = data2.get(Calendar.MONTH) + 1;

            System.out.println("Formatação: " + data);

        } catch (ParseException ex) {
            throw new Exception("Não foi possível extrair o Mês da competência " + competencia + ".\n" + ex);
        }

        return x;
    }

    public static int getYear(String competencia) throws Exception {
        int x = 0;

        try {
            data = DATEIN.parse("01/" + competencia);

            Calendar data2 = Calendar.getInstance();
            data2.setTime(data);

            x = data2.get(Calendar.YEAR);

            System.out.println("Formatação: " + data);

        } catch (ParseException ex) {
            throw new Exception("Não foi possível extrair o ano da competência " + competencia + ".\n" + ex);
        }

        return x;
    }

    /**
     * Define data de vencimento em dia útil. Recebe parâmetros, encontra data
     * de vencimento em dias corridos e valida se é dia útil.
     *
     * @param competencia Momento onde foi apurada a obrigação.
     * @param dia Dia do vencimento máximo da obrigação.
     * @param antecipa TRUE para encontrar dia útil anterior. FALSE para
     * encontrar dia útil posterior.
     * @return
     * @throws java.lang.Exception
     */
    public static Date vencimentoDiaUtil(String competencia, int dia, boolean antecipa) throws Exception {

        int x = dia;

        vencimentoDiasCorridos(competencia, x);

        while (data.getDay() == 0 | data.getDay() == 6) {
            if (antecipa) {
                x--;
            } else {
                x++;
            }
            vencimentoDiasCorridos(competencia, x);
        }

        return data;
    }

    /**
     * Define data de vencimento de acordo com o dia recebido. Não considera
     * dias úteis ou inúteis.
     *
     * @param competencia
     * @param dia
     * @return
     * @throws java.lang.Exception
     */
    public static Date vencimentoDiasCorridos(String competencia, int dia) throws Exception {

        try {
            data = DATEIN.parse(dia + "/" + competencia);

            Calendar data2 = Calendar.getInstance();
            data2.setTime(data);

            data2.set(Calendar.MONTH, data2.get(Calendar.MONTH) + 1);
            //data2.set(Calendar.DAY_OF_MONTH, data2.get(Calendar.DAY_OF_MONTH) - 1);

            data = data2.getTime();

            System.out.println("Formatação: " + DATEIN.format(data));
            System.out.println("Dia da Semana: " + data.getDay());

        } catch (ParseException ex) {
            throw new Exception("Não foi possível definir o vencimento em dias corridos a competência " + competencia + ".\n" + ex);
        }

        return data;
    }

    public static String dateBRstr(Date dt) {
        try {
            return DATEIN.format(dt);
        } catch (Exception e) {
            return null;
        }

    }

    public static String dateUSstr(String dtBR) {
        try {

            return DATEOUT.format(DATEIN.parse(dtBR));

        } catch (Exception e) {
            return null;
        }

    }

    public static String dateUSstr(Date dt) {
        return DATEOUT.format(dt);
    }

//    public static void main(String[] args) {
//        ManipulaData d = new ManipulaData();
//
//        try {
//            //System.out.println("Resposta: " + d.getYear("12/2017"));
//            //d.vencimentoDiasCorridos("10/2017", 12);
//            //d.vencimentoDiaUtil("10/2017", 11, false);
//            //d.ultimoDiaUtilMesPosterior("11/2017");
//
//            //d.getCompetenceBR(d.nextMonth("01/2010"));
//            //d.timeLapse(d.firstDay("01/2016"), d.lastDay("12/2017"), d.MONTHS);
//            d.nextMonth("01/2017");
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//
//    }
}
