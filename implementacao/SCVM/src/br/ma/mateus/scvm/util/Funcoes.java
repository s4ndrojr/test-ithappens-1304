package br.ma.mateus.scvm.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class Funcoes {
	public static final Integer itemMeioCultura = 14;

	public static String byteToString(byte[] vetor) {
		try {
			String str = Arrays.toString(vetor);
			str = str.substring(1, str.length() - 1);

			return str;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	
	public static Date montarFeriado(int dia, int mes, int ano) {
    	Calendar c = Calendar.getInstance();
    	
    	c.set(Calendar.YEAR, ano);
    	c.set(Calendar.MONTH, mes-1);
    	c.set(Calendar.DAY_OF_MONTH, dia);
    	
    	c.set(Calendar.HOUR_OF_DAY, 0);
    	c.set(Calendar.MINUTE, 0);
    	c.set(Calendar.SECOND, 0);
    	c.set(Calendar.MILLISECOND, 0);
    	
    	return c.getTime();
	}	

	
	public static Date zeroHoraDia(Date dia) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(dia);
    	c.set(Calendar.HOUR_OF_DAY, 0);
    	c.set(Calendar.MINUTE, 0);
    	c.set(Calendar.SECOND, 0);
    	c.set(Calendar.MILLISECOND, 0);
    	
    	return c.getTime();
	}
	
	public static Date ultimaHoraDia(Date dia) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(dia);
    	c.set(Calendar.HOUR_OF_DAY, 23);
    	c.set(Calendar.MINUTE, 59);
    	c.set(Calendar.SECOND, 59);
    	c.set(Calendar.MILLISECOND, 999);
    	
    	return c.getTime();
	}
	
	public static int getMinute(Date dia) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(dia);
    	
    	return c.get(Calendar.MINUTE);
	}
	
	
	public static Date inicioAtendimento(Date dia) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(dia);
    	c.set(Calendar.HOUR_OF_DAY, 8);
    	c.set(Calendar.MINUTE, 0);
    	c.set(Calendar.SECOND, 0);
    	c.set(Calendar.MILLISECOND, 0);
    	
    	return c.getTime();
	}
	
	public static Date fimAtendimento(Date dia) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(dia);
    	c.set(Calendar.HOUR_OF_DAY, 18);
    	c.set(Calendar.MINUTE, 0);
    	c.set(Calendar.SECOND, 0);
    	c.set(Calendar.MILLISECOND, 0);
    	
    	return c.getTime();
	}	
	
	public static Date somaDias(Date dia, int dias) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(dia);
    	c.add(Calendar.DAY_OF_MONTH, dias); 
    	
    	return c.getTime();
	}
	
	public static boolean horarioAlmoco(Date dia, String idEvento) {
		
    	Calendar lunchStart = Calendar.getInstance();
    	lunchStart.setTime(dia);
    	lunchStart.set(Calendar.HOUR_OF_DAY, 12);
    	lunchStart.set(Calendar.MINUTE, 00);
    	lunchStart.set(Calendar.SECOND, 00);
    	lunchStart.set(Calendar.MILLISECOND, 000);
    	Date lunchStartDate = lunchStart.getTime();
    	
    	Calendar lunchEnd = Calendar.getInstance();
    	lunchEnd.setTime(dia);        	
    	lunchEnd.set(Calendar.HOUR_OF_DAY, 14);
    	lunchEnd.set(Calendar.MINUTE, 00);
    	lunchEnd.set(Calendar.SECOND, 00);
    	lunchEnd.set(Calendar.MILLISECOND, 00); 
    	Date lunchEndDate = lunchEnd.getTime();		
    	
    	if( 
			(dia.before(lunchEndDate) && dia.after(lunchStartDate) && idEvento == null) || 
			(dia.equals(lunchStartDate))
    		) {
    		return true;
    	}else {
    		return false;
    	}
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, BigDecimal.ROUND_CEILING);
		return bd.doubleValue();
	}

	public static byte[] StringTobyte(String digital) {
		String[] bs = digital.split(",");
		int tam = bs.length;
		byte[] b = new byte[tam];

		for (int j = 0; j < tam; j++) {
			b[j] = Byte.parseByte(bs[j].trim());
		}

		return b;
	}

	public static byte[] imageToBytes(String inFile) throws IOException {
		InputStream is = null;
		byte[] buffer = null;

		is = new FileInputStream(inFile);
		buffer = new byte[is.available()];
		is.read(buffer);
		is.close();

		return buffer;
	}

	public String ConvertDateToString(Date data) throws Exception {
		if (data == null || data.equals(""))
			return null;

		String strData = null;
		try {
			SimpleDateFormat dateformatMMDDYYYY = new SimpleDateFormat("MM/dd/yyyy", new Locale("pt", "BR"));
			strData = dateformatMMDDYYYY.format(data);
		} catch (Exception e) {
			throw e;
		}

		return strData;
	}

	public static Date ConvertStringToDate(String data) throws Exception {
		if (data == null || data.equals(""))
			return null;

		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
			date = (java.util.Date) formatter.parse(data);
		} catch (ParseException e) {
			throw e;
		}
		return date;
	}

	public static List<String> listaDataAnos() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());

		List<String> listaDataAnos = new ArrayList<String>();
		for (Integer e = gc.get(Calendar.YEAR); e >= 2013; e--) {
			listaDataAnos.add(e.toString());
		}

		return listaDataAnos;
	}

	public static Vector<String> geraMeses(String fAno, String fAnoFinal, String fMes, String fMesFinal) {

		Vector<String> vtDatas = new Vector<String>();

		Integer anoI = Integer.parseInt(fAno);
		Integer anoF = Integer.parseInt(fAnoFinal);
		Integer mesI = Integer.parseInt(fMes);
		Integer mesF = Integer.parseInt(fMesFinal);

		for (int i = anoI; i <= anoF; i++) {
			int mesFinal = 12;
			if (i == anoF)
				mesFinal = mesF;
			for (int j = mesI; j <= mesFinal; j++) {
				String dataRef = j + "/" + i;
				if (j < 10) {
					dataRef = "0" + dataRef;
				}
				vtDatas.add(dataRef);
			}
			mesI = 1;
		}

		return vtDatas;
	}

	public static String obtemData(String data) {
		if (data.startsWith("Janeiro")) {
			return "01";
		}
		if (data.startsWith("Fevereiro")) {
			return "02";
		}
		if (data.startsWith("Março")) {
			return "03";
		}
		if (data.startsWith("Abril")) {
			return "04";
		}
		if (data.startsWith("Maio")) {
			return "05";
		}
		if (data.startsWith("Junho")) {
			return "06";
		}
		if (data.startsWith("Julho")) {
			return "07";
		}
		if (data.startsWith("Agosto")) {
			return "08";
		}
		if (data.startsWith("Setembro")) {
			return "09";
		}
		if (data.startsWith("Outubro")) {
			return "10";
		}
		if (data.startsWith("Novembro")) {
			return "11";
		}
		if (data.startsWith("Dezembro")) {
			return "12";
		}

		return "";
	}

	/*
	 * public static String numLotacaoPeloPerfilDeAcesso(TbUsuarios tbUsuario) {
	 * String numLotacao = "";
	 * 
	 * String perfil = tbUsuario.getPerfil();
	 * if(!perfil.equals("ROLE_DIRETOR_PRESIDENTE")) { numLotacao =
	 * tbUsuario.getNumLotacao();
	 * 
	 * if(tbUsuario.getNumLotacaoTransferido() != null &&
	 * !tbUsuario.getNumLotacaoTransferido().trim().equals("")) { numLotacao =
	 * tbUsuario.getNumLotacaoTransferido(); } }
	 * 
	 * return numLotacao; }
	 */
	public static String getMatriculaFormatada(String matricula) {
		String resultado = new String();

		try {
			resultado = String.format("%05d", Integer.parseInt(matricula));
		} catch (Exception e) {
			resultado = matricula.toUpperCase();
		}

		return resultado;
	}

	/**
	 * Verifica o Banco o qual o beneficiário possui conta
	 * 
	 * @param numeroBanco
	 * @return
	 */
	public static String verificaNomeBanco(String numeroBanco) {

		if (numeroBanco.compareTo("001") == 0 || numeroBanco.compareTo("1") == 0) {
			return "Banco do Brasil S/A";
		}

		if (numeroBanco.compareTo("003") == 0 || numeroBanco.compareTo("3") == 0) {
			return "Banco da Amazônia S/A";
		}

		if (numeroBanco.compareTo("004") == 0) {
			return "Banco do Nordeste do Brasil S/A";
		}

		if (numeroBanco.compareTo("033") == 0 || numeroBanco.compareTo("008") == 0) {
			return "Banco Santander (Brasil) S/A";
		}

		if (numeroBanco.compareTo("104") == 0) {
			return "Caixa Econômica Federal";
		}

		if (numeroBanco.compareTo("237") == 0) {
			return " Banco Bradesco S/A";
		}

		if (numeroBanco.compareTo("341") == 0) {
			return "Itaú Unibanco S/A";
		}

		if (numeroBanco.compareTo("399") == 0) {
			return "HSBC Bank Brasil S/A";
		}

		if (numeroBanco.compareTo("341") == 0) {
			return "Itaú Unibanco S/A";
		}

		if (numeroBanco.compareTo("422") == 0) {
			return "Banco Safra S/A";
		}

		if (numeroBanco.compareTo("453") == 0) {
			return "Banco Rural S/A";
		}

		if (numeroBanco.compareTo("623") == 0) {
			return " Banco Panamericano S/A";
		}

		if (numeroBanco.compareTo("745") == 0) {
			return "Banco Citibank S/A";
		}

		return "OUTROS";
	}

	public static String getLotacaoFormatada(String siglaLotacao, String nomeLotacao) {
		String lotacaoFormatada = "";

		if (!siglaLotacao.trim().equals("")) {
			lotacaoFormatada = siglaLotacao + " - ";
		}
		lotacaoFormatada += nomeLotacao;

		return lotacaoFormatada;
	}

	public static String getPerfilFormatado(String perfilOriginal) {
		String perfil = null;

		if (perfilOriginal.equals("ROLE_GERENTE")) {
			perfil = "GERENTE";
		} else if (perfilOriginal.equals("ROLE_USER")) {
			perfil = "USUÁRIO COMUM";
		} else if (perfilOriginal.equals("ROLE_DIRETOR")) {
			perfil = "DIRETOR";
		} else if (perfilOriginal.equals("ROLE_DIRETOR_PRESIDENTE")) {
			perfil = "DIRETOR PRESIDENTE";
		} else if (perfilOriginal.equals("ROLE_COORDENADOR")) {
			perfil = "COORDENADOR";
		} else if (perfilOriginal.equals("ROLE_SUPERINTENDENTE")) {
			perfil = "SUPERINTENDENTE";
		} else if (perfilOriginal.equals("ROLE_CHEFE")) {
			perfil = "CHEFE DE SETOR";
		}

		return perfil;
	}

	public static String getPerfilOriginal(String perfilFormatado) {
		String perfil = null;

		if (perfilFormatado.equals("GERENTE")) {
			perfil = "ROLE_GERENTE";
		} else if (perfilFormatado.equals("USUÁRIO COMUM")) {
			perfil = "ROLE_USER";
		} else if (perfilFormatado.equals("DIRETOR")) {
			perfil = "ROLE_DIRETOR";
		} else if (perfilFormatado.equals("DIRETOR PRESIDENTE")) {
			perfil = "ROLE_DIRETOR_PRESIDENTE";
		} else if (perfilFormatado.equals("COORDENADOR")) {
			perfil = "ROLE_COORDENADOR";
		} else if (perfilFormatado.equals("SUPERINTENDENTE")) {
			perfil = "ROLE_SUPERINTENDENTE";
		} else if (perfilFormatado.equals("CHEFE DE SETOR")) {
			perfil = "ROLE_CHEFE";
		}

		return perfil;
	}

	/*
	 * public static List<TbLotacao> montarComboLotacao() { List<TbLotacao>
	 * listaTbLotacao = new ArrayList<TbLotacao>(); TbLotacaoDAO tbLotacaoDAO =
	 * (TbLotacaoDAO) TbLotacaoDAO.getInstance();
	 * 
	 * listaTbLotacao = tbLotacaoDAO.listaLotacaoCombo();
	 * 
	 * return listaTbLotacao; }
	 */
	public static String limpaFormato(String numero) {

		numero = numero.replace(".", "");
		numero = numero.replace(",", ".");

		return numero;
	}

	public static String formataMoeda(Double numero) {
		NumberFormat format = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

		return format.format(numero);
	}

	public static String formataMoeda(BigDecimal numero) {
		NumberFormat format = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

		return format.format(numero);
	}

	public static Double formataMoeda(String numero) {
		numero = limpaFormato(numero);

		return Double.valueOf(numero);
	}

	public static Date formataStringToDate(String data) {

		String oldFormat = "dd/MM/yyyy";
		String newFormat = "yyyy-MM-dd HH:mm:ss";

		SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
		SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

		Date novaData = null;

		try {
			novaData = sdf2.parse(sdf2.format(sdf1.parse(data)));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return novaData;

	}

	public static String formataDateParaString(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(data);
	}

	public static String obtemDataMes(int mes) {
		String strMes = "";
		switch (mes) {
		case 1:
			strMes = "JANEIRO";
			break;
		case 2:
			strMes = "FEVEREIRO";
			break;
		case 3:
			strMes = "MARÇO";
			break;
		case 4:
			strMes = "ABRIL";
			break;
		case 5:
			strMes = "MAIO";
			break;
		case 6:
			strMes = "JUNHO";
			break;
		case 7:
			strMes = "JULHO";
			break;
		case 8:
			strMes = "AGOSTO";
			break;
		case 9:
			strMes = "SETEMBRO";
			break;
		case 10:
			strMes = "OUTUBRO";
			break;
		case 11:
			strMes = "NOVEMBRO";
			break;
		case 12:
			strMes = "DEZEMBRO";
			break;
		}

		return strMes;
	}

	public static boolean validacaoCPF(String s_aux) {
		// ------- Rotina para CPF
		if (s_aux.length() == 11) {
			int d1, d2, d3;
			int digito1, digito2, resto;
			int digitoCPF;
			String nDigResult;
			String nDigVerif = s_aux.substring(0, 1);
			d1 = d2 = 0;
			d3 = 1;
			digito1 = digito2 = resto = 0;
			boolean digVerifIgual = true;

			for (int n_Count = 1; n_Count < s_aux.length() - 1; n_Count++) {
				digitoCPF = Integer.valueOf(s_aux.substring(n_Count - 1, n_Count)).intValue();
				// --------- Multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e
				// assim por diante.
				d1 = d1 + (11 - n_Count) * digitoCPF;
				// --------- Para o segundo digito repita o procedimento incluindo o primeiro
				// digito calculado no passo anterior.
				d2 = d2 + (12 - n_Count) * digitoCPF;
			}
			// --------- Primeiro resto da divisï¿½o por 11.
			resto = d1 % 11;
			// --------- Se o resultado for 0 ou 1 o digito ï¿½ 0 caso contrï¿½rio o digito
			// ï¿½ 11 menos o resultado anterior.
			if (resto < 2)
				digito1 = 0;
			else
				digito1 = 11 - resto;
			d2 += 2 * digito1;
			// --------- Segundo resto da divisï¿½o por 11.
			resto = d2 % 11;
			// --------- Se o resultado for 0 ou 1 o digito ï¿½ 0 caso contrï¿½rio o digito
			// ï¿½ 11 menos o resultado anterior.
			if (resto < 2)
				digito2 = 0;
			else
				digito2 = 11 - resto;
			// --------- Digito verificador do CPF que esta sendo validado.
			String nDigVerific = s_aux.substring(s_aux.length() - 2, s_aux.length());
			// --------- Concatenando o primeiro resto com o segundo.
			nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
			// --------- Comparar o digito verificador do cpf com o primeiro resto + o
			// segundo resto.

			// ---------- Verifica se todos os dï¿½gitos estao repetidos
			while (d3 < s_aux.length()) {

				if (!s_aux.substring(d3, d3 + 1).equals(nDigVerif)) {
					digVerifIgual = false;
				}
				d3 += 1;
			}

			if (digVerifIgual) {
				return false;
			}

			return nDigVerific.equals(nDigResult);
		} else
			return false;
	}

	/**
	 * Validaï¿½ï¿½o de CNPJ
	 *
	 * @author Raphael Rossiter
	 * @date 21/10/2008
	 *
	 * @param s_aux
	 * @return boolean
	 */
	public static boolean validacaoCNPJ(String s_aux) {

		// -------- Rotina para CNPJ
		if (s_aux.length() == 14 && !s_aux.equals("00000000000000")) {
			int soma = 0, dig;
			String cnpj_calc = s_aux.substring(0, 12);
			char[] chr_cnpj = s_aux.toCharArray();
			// --------- Primeira parte
			for (int i = 0; i < 4; i++)
				if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
					soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
			for (int i = 0; i < 8; i++)
				if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
					soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
			dig = 11 - (soma % 11);
			cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
			// --------- Segunda parte
			soma = 0;
			for (int i = 0; i < 5; i++)
				if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
					soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
			for (int i = 0; i < 8; i++)
				if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
					soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
			dig = 11 - (soma % 11);
			cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
			return s_aux.equals(cnpj_calc);
		} else
			return false;

	}
	
	public static String formatarData(Date data) {
		String retorno = "";
		if (data != null) { // 1
			Calendar dataCalendar = new GregorianCalendar();
			StringBuffer dataBD = new StringBuffer();

			dataCalendar.setTime(data);

			if (dataCalendar.get(Calendar.DAY_OF_MONTH) > 9) {
				dataBD.append(dataCalendar.get(Calendar.DAY_OF_MONTH) + "/");
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.DAY_OF_MONTH)
						+ "/");
			}

			// Obs.: Janeiro no Calendar ï¿½ mï¿½s zero
			if ((dataCalendar.get(Calendar.MONTH) + 1) > 9) {
				dataBD.append(dataCalendar.get(Calendar.MONTH) + 1 + "/");
			} else {
				dataBD.append("0" + (dataCalendar.get(Calendar.MONTH) + 1)
						+ "/");
			}

			dataBD.append(dataCalendar.get(Calendar.YEAR));
			retorno = dataBD.toString();
		}
		return retorno;
	}
	
	public static String formatarHoraSemData(Date data) {
		StringBuffer dataBD = new StringBuffer("");

		if (data != null) {

			Calendar dataCalendar = new GregorianCalendar();
			dataCalendar.setTime(data);

			if (dataCalendar.get(Calendar.HOUR_OF_DAY) > 9) {
				dataBD.append(dataCalendar.get(Calendar.HOUR_OF_DAY));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.HOUR_OF_DAY));
			}

			dataBD.append(":");

			if (dataCalendar.get(Calendar.MINUTE) > 9) {
				dataBD.append(dataCalendar.get(Calendar.MINUTE));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.MINUTE));
			}

			dataBD.append(":");

			if (dataCalendar.get(Calendar.SECOND) > 9) {
				dataBD.append(dataCalendar.get(Calendar.SECOND));
			} else {
				dataBD.append("0" + dataCalendar.get(Calendar.SECOND));
			}

		}

		return dataBD.toString();
	}

	public static Date toDate(LocalDateTime localDateTime) {
		LocalTime localTime = localDateTime.toLocalTime();
		Instant instant = localTime
				.atDate(LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth()))
				.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);
		return data;
	}
	
	public static Date toDate(LocalDate localDate) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date data = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		return data;
	}
	
	public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}

	public static Date toDate(Instant instant) {
		BigInteger milis = BigInteger.valueOf(instant.getEpochSecond()).multiply(BigInteger.valueOf(1000));
		milis = milis.add(BigInteger.valueOf(instant.getNano()).divide(BigInteger.valueOf(1_000_000)));
		return new Date(milis.longValue());
	}
}