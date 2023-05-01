package com.demo.service.formatos;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.RecepcionVerificacionRegistroCodificacion;
import com.demo.model.operacion.SolicitudServicioCliente;
import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.RecepcionVerificacionRegistroCodificacionService;
import com.demo.service.operacion.SolicitudServicioClienteMuestrasService;
import com.demo.service.operacion.SolicitudServicioClienteService;
import com.demo.utils.EstructuraNombres;
import com.demo.utils.GenerateQR;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;

@Service
public class FEIL_MIE_007_Service {

    @Autowired
    private SolicitudServicioClienteService solicitudServicioClienteService;

    @Autowired
    private SolicitudServicioClienteMuestrasService solicitudServicioClienteMuestrasService;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    @Autowired
    private RecepcionVerificacionRegistroCodificacionService recepcionVerificacionRegistroCodificacionService;

    EstructuraNombres estructuraNombres = new EstructuraNombres();

    public ResponseEntity<InputStreamResource> crearFormato(Long id, int band) throws InvalidFormatException, IOException {
        List<MetodoMuestra> lista;
        RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion = new RecepcionVerificacionRegistroCodificacion();
        if (band == 1){
            recepcionVerificacionRegistroCodificacion = recepcionVerificacionRegistroCodificacionService.findById(id);
            lista = metodoMuestraService.findAllByMuestra(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getSolicitudServicioClienteMuestrasId());
            //SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteService.findById(id);
        } if (band == 3){
            SolicitudServicioCliente solicitudServicioCliente = solicitudServicioClienteService.findById(id);
            lista = metodoMuestraService.findAllBySolicitud(solicitudServicioCliente);

        } else {
            lista = metodoMuestraService.findAllById(id);
            recepcionVerificacionRegistroCodificacion = recepcionVerificacionRegistroCodificacionService.findBySolicitudServicioClienteMuestrasId(lista.get(0).getSolicitudServicioClienteMuestras().getSolicitudServicioClienteMuestrasId());
        }


        URL url = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/etiquetas/FEIL-MIE-007.docx");
        URL url2 = new URL("https://resources.adpmx.com/cecim/laboratorio/doc/etiquetas/FEIL-MIE-007-plantilla.docx");
        XWPFDocument doc = new XWPFDocument(url.openStream());
        XWPFDocument plantilla = new XWPFDocument(url2.openStream());

//        String pathLista = "/documentos/FEIL_MIE_007/FEIL-MIE-007-" + lista.size() + ".docx";
//        ClassPathResource resource = new ClassPathResource(pathLista);
//        XWPFDocument doc = new XWPFDocument(resource.getInputStream());

//        XWPFTable table;
//        for (int i = 0; i < lista.size(); i++) {
//            table = doc.getTables().get(i);
//            if (band !=3 ){
//                table.getRow(1).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getIdInternoMuestra1());
//                table.getRow(2).getCell(1).setText(lista.get(i).getMethod().getCodigoMetodo());
//                table.getRow(3).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getObservaciones());
//
//                XWPFParagraph paragraph = table.getRow(4).getCell(1).addParagraph();
//                XWPFRun run = paragraph.createRun();
//                //FileInputStream fis = new FileInputStream(lista.get(i).getPathQRLab());
//                InputStream fis = new URL(lista.get(i).getPathQRLab()).openStream();
//                XWPFPicture picture = run.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(130), Units.pixelToEMU(130));
//            } else {
//                try{
//                    RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion1 = recepcionVerificacionRegistroCodificacionService.findBySolicitudServicioClienteMuestrasId(lista.get(i).getSolicitudServicioClienteMuestras().getSolicitudServicioClienteMuestrasId());
//                    table.getRow(1).getCell(1).setText(recepcionVerificacionRegistroCodificacion1.getIdInternoMuestra1());
//                    table.getRow(2).getCell(1).setText(lista.get(i).getMethod().getCodigoMetodo());
//                    table.getRow(3).getCell(1).setText(recepcionVerificacionRegistroCodificacion1.getSolicitudServicioClienteMuestras().getObservaciones());
//
//                    XWPFParagraph paragraph = table.getRow(4).getCell(1).addParagraph();
//                    XWPFRun run = paragraph.createRun();
//                    //FileInputStream fis = new FileInputStream(lista.get(i).getPathQRLab());
//                    InputStream fis = new URL(lista.get(i).getPathQRLab()).openStream();
//                    XWPFPicture picture = run.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(130), Units.pixelToEMU(130));
//                } catch (NullPointerException e){
//                    System.out.println("No se han hecho todas las recepciones");
//                }
//            }
//        }




        int contadorMuestrasTablas = 0;
        for (int i = 0; i< lista.size(); i++){
            XWPFTable table = doc.createTable();
            table.removeRow(0); // El default row no es necesario
            XWPFTable tablePlantilla = plantilla.getTables().get(0);

            CTTbl cTTblPlantilla = tablePlantilla.getCTTbl();
            table = new XWPFTable((CTTbl) cTTblPlantilla.copy(), doc);
//            table.removeRow(2);
            //table_6.removeRow(1);
            if (band != 3) {

                table.getRow(0).getCell(0).removeParagraph(0);
                XWPFParagraph paragraph1 = table.getRow(0).getCell(0).addParagraph();
                paragraph1.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun runlogo = paragraph1.createRun();
                runlogo.removeBreak();
                InputStream inputStream = new URL("https://resources.adpmx.com/cecim/laboratorio/img/logos/LogoFEIL-MIE-007.png").openStream();
                XWPFPicture pic = runlogo.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(100), Units.pixelToEMU(60));


                table.getRow(1).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getIdInternoMuestra1());
                table.getRow(2).getCell(1).setText(lista.get(i).getMethod().getCodigoMetodo());
                table.getRow(3).getCell(1).setText(recepcionVerificacionRegistroCodificacion.getSolicitudServicioClienteMuestras().getObservaciones());

                XWPFParagraph paragraph = table.getRow(4).getCell(1).addParagraph();
                XWPFRun run = paragraph.createRun();
                InputStream fis = new URL(lista.get(i).getPathQRLab()).openStream();
                XWPFPicture picture = run.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(130), Units.pixelToEMU(130));
            } else {
                try{
                    RecepcionVerificacionRegistroCodificacion recepcionVerificacionRegistroCodificacion1 = recepcionVerificacionRegistroCodificacionService.findBySolicitudServicioClienteMuestrasId(lista.get(i).getSolicitudServicioClienteMuestras().getSolicitudServicioClienteMuestrasId());
                    table.getRow(0).getCell(0).removeParagraph(0);
                    XWPFParagraph paragraph1 = table.getRow(0).getCell(0).addParagraph();
                    paragraph1.setAlignment(ParagraphAlignment.CENTER);
                    XWPFRun runlogo = paragraph1.createRun();
                    runlogo.removeBreak();
                    InputStream inputStream = new URL("https://resources.adpmx.com/cecim/laboratorio/img/logos/LogoFEIL-MIE-007.png").openStream();
                    XWPFPicture pic = runlogo.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(100), Units.pixelToEMU(60));

                    table.getRow(1).getCell(1).setText(recepcionVerificacionRegistroCodificacion1.getIdInternoMuestra1());
                    table.getRow(2).getCell(1).setText(lista.get(i).getMethod().getCodigoMetodo());
                    table.getRow(3).getCell(1).setText(recepcionVerificacionRegistroCodificacion1.getSolicitudServicioClienteMuestras().getObservaciones());
                    XWPFParagraph paragraph = table.getRow(4).getCell(1).addParagraph();
                    XWPFRun run = paragraph.createRun();
                    InputStream fis = new URL(lista.get(i).getPathQRLab()).openStream();
                    XWPFPicture picture = run.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, "Name", Units.pixelToEMU(130), Units.pixelToEMU(130));
                } catch (NullPointerException e){
                    System.out.println("No se han hecho todas las recepciones");
                }
            }

            doc.setTable(contadorMuestrasTablas, table);

//            XWPFParagraph para3 = doc.createParagraph();
//            XWPFRun run3 = para3.createRun();
//            run3.addBreak();
            contadorMuestrasTablas++;
            System.out.println("Se generó etiqueta --> " + contadorMuestrasTablas);
        }


        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=FEIL_MIE_" + lista.get(0).getSolicitudServicioClienteMuestras().getSolicitudServicioCliente().getFolioSolitudServicioCliente() + ".docx");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        doc.write(byteArrayOutputStream);
        doc.close();
        MediaType word = MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(word)
                .body(new InputStreamResource(byteArrayInputStream));
    }

}
