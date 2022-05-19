package com.example.ktmblockchain;

import com.example.ktmblockchain.bean.PdfData;
import com.example.ktmblockchain.chain.Block;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Scope("singleton")
public class ChainManager {

    ArrayList<Block> list;

    public ChainManager() {
        try {
            list = readChainFromFile();
        } catch (IOException ex) {
            list = new ArrayList<>();
            Block genesisBlock = new Block(null, "0a-sd.aksdmoiqwndpuoANdsijönbaijsbniaBSHKDbSADhkaBDOÖASD");
            list.add(genesisBlock);
        }
    }

    private ArrayList<Block> readChainFromFile() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("blocksave.json")));
        StringBuilder stringBuilder = new StringBuilder();
        while (br.ready()) {
            stringBuilder.append(br.readLine());
        }
        return new Gson().fromJson(stringBuilder.toString(), new TypeToken<ArrayList<Block>>() {
        }.getType());
    }

    private void writeChainToFile() throws FileNotFoundException {
        String chainAsJson = new Gson().toJson(list);
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream("blocksave.json")));
        printWriter.write(chainAsJson);
        printWriter.flush();
        printWriter.close();
    }

    public String addBlock(PdfData pdfData) {
        PdfDocument pdfDocument = new PdfDocument();
        Block newBlock = new Block(list.get(list.size() - 1).getHash(), pdfDocument.hashCode());
        list.add(newBlock);
        try {
            writeChainToFile();
            printChain();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

// Prepare the IXDocReport instance based on the template, using
// Freemarker template engine
        return loadDocument();
    }

    private void printChain() {
        for (Block block : list) {
            System.out.println(block + " ->");
        }
    }

    public Boolean validateBlock(String hash) {
        for (Block block : list) {
            if (block.getHash().equals(hash)) {
                return true;
            }
        }
        return false;
    }

    private Object convertDocument(PdfData pdfData) {
        InputStream in = null;
        try {
            String p = this.getClass().getClassLoader().getResource("certificate_template.odt").toString();
            p = p.replace("file:", "");
            File file = new File(p);
            System.out.println(file.getAbsolutePath());
            in = new FileInputStream(file);
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
            Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.ODFDOM);
            IContext ctx = report.createContext();
            ctx.put("re_wo", pdfData.getReifen());
            ctx.put("mo_wo", pdfData.getMotor());
            ctx.put("le_wo", pdfData.getLenkung());
            ctx.put("ra_wo", pdfData.getRadiator());
            ctx.put("br_wo", pdfData.getBremse());
            ctx.put("gr_wo", pdfData.getGetriebe());
            ctx.put("fe_wo", pdfData.getFedergabel());
            ctx.put("ke_wo", pdfData.getKette_ritzerl());
            ctx.put("ga_wo", pdfData.getGashebel());
            FieldsMetadata metadata = report.createFieldsMetadata();
/*
            ctx.put("to", invoice.getTo());
            ctx.put("sender", invoice.getInvoicer());
            ctx.put("r", invoice.getInvoiceRows());*/
            report.convert(ctx, options, new FileOutputStream("result.pdf"));
        } catch (FileNotFoundException exx) {
            System.err.println(exx.getMessage());
        } catch (IOException | XDocReportException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private String loadDocument() {
        return "result.pdf";
    }

    public static Document showHelp(Block emp) throws Exception {
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream("C:/tmp/report.pdf"));
        document.open();
        document.add(new Paragraph("table"));
        document.add(new Paragraph(new Date().toString()));
        PdfPTable table = new PdfPTable(2);

        PdfPCell cell = new PdfPCell(new Paragraph("table"));

        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10.0f);
        cell.setBackgroundColor(new Color(140, 221, 8));

        table.addCell(cell);
        ArrayList<String[]> row = new ArrayList<String[]>();
        String[] data = new String[2];
        data[0] = "1";
        data[1] = "2";
        String[] data1 = new String[2];
        data1[0] = "3";
        data1[1] = "4";
        row.add(data);
        row.add(data1);

        for (int i = 0; i < row.size(); i++) {
            String[] cols = row.get(i);
            for (int j = 0; j < cols.length; j++) {
                table.addCell(cols[j]);
            }
        }
        document.add(table);
        document.close();

        return document;
    }
}
