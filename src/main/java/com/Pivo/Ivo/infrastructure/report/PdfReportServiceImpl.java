package com.Pivo.Ivo.infrastructure.report;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Pivo.Ivo.api.DTO.ToDoPdf;
import com.Pivo.Ivo.domain.service.PdfReportService;
import com.Pivo.Ivo.domain.service.ToDoService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfReportServiceImpl implements PdfReportService {

	@Autowired
	private ToDoService service;
	
	@Override
	public byte[] pdfReport() {
		
		try {
		
			var inputStream = this.getClass().getResourceAsStream("/relatorios/tarefas.jasper");
			
			
			var parametros = new HashMap<String, Object>();
			parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
			
			List<ToDoPdf> toDo = service.findRentForPdf(); 
			
			var dataSource = new JRBeanCollectionDataSource(toDo);
			
			
			var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

			
				return JasperExportManager.exportReportToPdf(jasperPrint);
				
			} catch (JRException e) {
				throw new ReportException("Não foi possivel gerar a lista de tarefas", e);
			}
		}
}


