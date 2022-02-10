package uz.pdp.springsecurity.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.util.FileCopyUtils;
import uz.pdp.springsecurity.entity.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PDFService {
    static String path = "src/main/resources/invoice.pdf";

    public void createPdf(Trade trade, HttpServletResponse response) throws IOException {

        Address address = trade.getAddress();
        PdfWriter writer = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(writer);
        pdfDocument.addNewPage();
        Document document = new Document(pdfDocument);

        Paragraph paragraph = new Paragraph(trade.getBranch().getName());
        paragraph.setFontSize(40);
        paragraph.setTextAlignment(TextAlignment.CENTER);

        Paragraph paragraph1 = new Paragraph(address.getCity() + ", " + address.getDistrict() + ", " + address.getStreet() + ", " + address.getHome());
        paragraph1.setFontSize(12);
        paragraph1.setMarginTop(-12);
        paragraph1.setMarginBottom(-7);
        paragraph1.setTextAlignment(TextAlignment.CENTER);


        Text text = new Text("Check");
        text.setFontSize(24);

        Paragraph paragraph2 = new Paragraph(text);
        paragraph2.setTextAlignment(TextAlignment.CENTER);

        Text id = new Text(String.valueOf(trade.getId()));
        Text text1 = new Text("Check N: ");
        text1.setBold();
        text1.setFontSize(14);
        Paragraph paragraph3 = new Paragraph();
        paragraph3.add(text1);
        paragraph3.add(id);


        String dateDB = String.valueOf(trade.getPayDate());
        String substring = dateDB.substring(0, 16);
        Paragraph date = new Paragraph();
        Text dateTime = new Text(substring);
        Text dateText = new Text("Sana: ");
        dateText.setBold();
        dateText.setFontSize(14);
        date.add(dateText);
        date.add(dateTime);

        Paragraph customer = new Paragraph();
        Text custValue = new Text(trade.getCustomer().getName());
        Text custText = new Text("Mijoz: ");
        custText.setFontSize(14);
        custText.setBold();
        customer.add(custText);
        customer.add(custValue);

        Paragraph seller = new Paragraph();
        Text sellerText = new Text("Sotuvchi: ");
        Text sellerValue = new Text(trade.getTrader().getFirstName());
        sellerText.setFontSize(14);
        sellerText.setBold();
        seller.add(sellerText);
        seller.add(sellerValue);

        Table header = new Table(2);
        Cell row1 = new Cell();
        row1.add(paragraph3);
        row1.add(customer);
        Cell row2 = new Cell();
        row2.add(date);
        row2.add(seller);
        row1.setBorder(Border.NO_BORDER);
        row2.setBorder(Border.NO_BORDER);
        row2.setTextAlignment(TextAlignment.RIGHT);
        row1.setWidth(new UnitValue(UnitValue.PERCENT, 50));
        row2.setWidth(new UnitValue(UnitValue.PERCENT, 50));
        header.setWidth(new UnitValue(UnitValue.PERCENT, 100));
        header.addCell(row1);
        header.addCell(row2);

        Table table = new Table(2);
        Text productText = new Text("Maxsulot: ");
        productText.setBold();
        Text quantityText = new Text("Miqdor");
        quantityText.setBold();
        Text unitText = new Text("Unit Price");
        unitText.setBold();
        Text totalText = new Text("Total Sum");
        totalText.setBold();
        custText.setFontSize(14);
        custText.setBold();
        Cell col1 = new Cell();
        Cell col2 = new Cell();
        col1.add(new Paragraph(productText));

        Table columns = new Table(3);
        Cell quantity = new Cell();
        quantity.add(new Paragraph(quantityText));
        Cell unitPrice = new Cell();
        unitPrice.add(new Paragraph(unitText));
        Cell totalSum = new Cell();
        totalSum.add(new Paragraph(totalText));
        columns.addCell(quantity);
        columns.addCell(unitPrice);
        columns.addCell(totalSum);
        col2.add(columns);

        col1.setBorder(Border.NO_BORDER);
        col2.setBorder(Border.NO_BORDER);
        quantity.setBorder(Border.NO_BORDER);
        unitPrice.setBorder(Border.NO_BORDER);
        totalSum.setBorder(Border.NO_BORDER);
        table.setBorderTop(new SolidBorder(1));
        quantity.setBorderRight(new SolidBorder(1));
        quantity.setBorderLeft(new SolidBorder(1));
        unitPrice.setBorderRight(new SolidBorder(1));

        Text subtotalText = new Text("Subtotal: ");
        subtotalText.setBold();
        subtotalText.setFontSize(14);
        Text totalsum = new Text("Total: ");
        totalsum.setFontSize(14);
        totalsum.setBold();
        Cell sub = new Cell();
        sub.add(new Paragraph(subtotalText));
        sub.add(new Paragraph(totalsum));


        Double subTotal = 0D;
        List<TradeProduct> tradeProductList = trade.getTradeProductList();
        for (TradeProduct tradeProduct : tradeProductList) {
            col1.add(new Paragraph(new Text(tradeProduct.getProduct().getName())));

            quantity.add(new Paragraph(new Text(String.valueOf(tradeProduct.getTradedQuantity()))));
            unitPrice.add(new Paragraph(new Text(String.valueOf(tradeProduct.getProduct().getSalePrice()))));
            totalSum.add(new Paragraph(new Text(String.valueOf(tradeProduct.getTradedQuantity() * tradeProduct.getProduct().getSalePrice()))));
            subTotal = subTotal + tradeProduct.getTradedQuantity();
        }
        Cell value = new Cell();
        Text subtotalValue = new Text(String.valueOf(subTotal));
        subtotalValue.setFontSize(14);
        String type = "";
        if (trade.getCurrency().getName().equals("DOLLAR")) {
            type = "$";
        } else {
            type = "so'm";
        }
        Text totalsumValue = new Text(String.valueOf(trade.getTotalSum()) + " " + type);
        totalsumValue.setFontSize(14);

        columns.addCell(quantity);
        columns.addCell(unitPrice);
        columns.addCell(totalSum);

        table.addCell(col1);
        table.addCell(col2);

        Table total = new Table(2);
        value.add(new Paragraph(subtotalValue));
        value.add(new Paragraph(totalsumValue));


        total.addCell(sub);
        total.addCell(value);

        total.setWidth(new UnitValue(UnitValue.PERCENT, 50));
        value.setWidth(new UnitValue(UnitValue.PERCENT, 50));
        sub.setWidth(new UnitValue(UnitValue.PERCENT, 50));

        value.setTextAlignment(TextAlignment.RIGHT);
        sub.setTextAlignment(TextAlignment.LEFT);

        total.setMarginTop(50);
        sub.setBorder(Border.NO_BORDER);
        value.setBorder(Border.NO_BORDER);
        total.setBorderTop(new SolidBorder(1));

        table.setWidth(new UnitValue(UnitValue.PERCENT, 100));
        col1.setWidth(new UnitValue(UnitValue.PERCENT, 50));
        col2.setWidth(new UnitValue(UnitValue.PERCENT, 50));
        columns.setWidth(new UnitValue(UnitValue.PERCENT, 100));
        columns.setTextAlignment(TextAlignment.CENTER);
        table.setMarginTop(30);

        Paragraph right = new Paragraph();
        right.setTextAlignment(TextAlignment.RIGHT);
        right.add(total);


        document.add(paragraph);
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(header);
        document.add(table);
        document.add(right);

        document.close();

        File file = new File(path);
        byte[] aByte = getByte(file);

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());

        long size = Files.size(Path.of(path));

        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", file.getName() + "/:" + size);
        FileCopyUtils.copy(aByte, response.getOutputStream());

        file.delete();

    }

    public static byte[] getByte(File file)
            throws IOException {
        FileInputStream fl = new FileInputStream(file);
        byte[] arr = new byte[(int) file.length()];
        fl.read(arr);
        fl.close();

        return arr;
    }


    public void createPdfPurchase(Purchase purchase, HttpServletResponse response) throws IOException {


        PdfWriter writer = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(writer);
        pdfDocument.addNewPage();
        Document document = new Document(pdfDocument);

        Paragraph paragraph = new Paragraph(purchase.getBranch().getName());
        paragraph.setFontSize(40);
        paragraph.setTextAlignment(TextAlignment.CENTER);

        Text text = new Text("Chek");
        text.setFontSize(24);

        Paragraph chek = new Paragraph(text);
        chek.setTextAlignment(TextAlignment.CENTER);

        Text id = new Text(String.valueOf(purchase.getId()));
        Text text1 = new Text("Chek N: ");
        text1.setBold();
        text1.setFontSize(14);
        Paragraph purchaseId = new Paragraph();
        purchaseId.add(text1);
        purchaseId.add(id);


        Paragraph date = new Paragraph();
        Text dateTime = new Text(String.valueOf(purchase.getDate()));
        Text dateText = new Text("Sana: ");
        dateText.setBold();
        dateText.setFontSize(14);
        date.add(dateText);
        date.add(dateTime);
        date.setMarginTop(15);

        Paragraph customer = new Paragraph();
        Text custValue = new Text(purchase.getDealer().getName());
        Text custText = new Text("Ta'minotchi: ");
        custText.setFontSize(14);
        custText.setBold();
        customer.add(custText);
        customer.add(custValue);

        Paragraph seller = new Paragraph();
        Text sellerText = new Text("Qabul qiluvchi: ");
        Text sellerValue = new Text(purchase.getSeller().getFirstName());
        sellerText.setFontSize(14);
        sellerText.setBold();
        seller.add(sellerText);
        seller.add(sellerValue);
        seller.setMarginTop(15);

        Paragraph phoneNumber = new Paragraph();
        Text phoneText = new Text("Telefon raqami: ");
        Text phoneValue = new Text(purchase.getDealer().getPhoneNumber());
        phoneText.setFontSize(14);
        phoneText.setBold();
        phoneNumber.add(phoneText);
        phoneNumber.add(phoneValue);
        phoneNumber.setMarginTop(15);

        Paragraph payStatus = new Paragraph();
        Text payStatusText = new Text("To'lov statusi: ");
        Text payStatusValue = new Text(purchase.getPaymentStatus().getStatus());
        payStatusText.setFontSize(14);
        payStatusText.setBold();
        payStatus.add(payStatusText);
        payStatus.add(payStatusValue);
        payStatus.setMarginTop(15);

        Table table1 = new Table(2);
        Cell col1 = new Cell();
        Cell col2 = new Cell();
        col1.add(purchaseId);
        col1.add(date);
        col1.add(payStatus);
        col2.add(customer);
        col2.add(phoneNumber);
        col2.add(seller);
        table1.addCell(col1);
        table1.addCell(col2);
        table1.setWidth(new UnitValue(UnitValue.PERCENT, 100));
        table1.setMarginTop(30);

        col1.setWidth(new UnitValue(UnitValue.PERCENT, 50));
        col2.setWidth(new UnitValue(UnitValue.PERCENT, 50));

        col1.setBorder(Border.NO_BORDER);
        col2.setBorder(Border.NO_BORDER);


        Table purchaseTable = new Table(8);
        Cell cel1 = new Cell().add(new Paragraph(new Text("T/r").setBold().setFontSize(14)));
        Cell cel2 = new Cell().add(new Paragraph(new Text("Maxsulot nomi").setBold().setFontSize(14)));
        Cell cel3 = new Cell().add(new Paragraph(new Text("Shtrix kodi").setBold().setFontSize(14)));
        Cell cel4 = new Cell().add(new Paragraph(new Text("Miqdori").setBold().setFontSize(14)));
        Cell cel5 = new Cell().add(new Paragraph(new Text("Narxi").setBold().setFontSize(14)));
        Cell cel6 = new Cell().add(new Paragraph(new Text("Soliq").setBold().setFontSize(14)));
        Cell cel7 = new Cell().add(new Paragraph(new Text("Umumiy summa").setBold().setFontSize(14)));
        cel1.setBorder(Border.NO_BORDER);
        cel2.setBorder(Border.NO_BORDER);
        cel3.setBorder(Border.NO_BORDER);
        cel4.setBorder(Border.NO_BORDER);
        cel5.setBorder(Border.NO_BORDER);
        cel6.setBorder(Border.NO_BORDER);
        cel7.setBorder(Border.NO_BORDER);
        cel2.setBorderLeft(new SolidBorder(1));
        cel3.setBorderLeft(new SolidBorder(1));
        cel4.setBorderLeft(new SolidBorder(1));
        cel5.setBorderLeft(new SolidBorder(1));
        cel6.setBorderLeft(new SolidBorder(1));
        cel7.setBorderLeft(new SolidBorder(1));
        Integer tr = 1;
        for (PurchaseProduct purchaseProduct : purchase.getPurchaseProductList()) {
            cel1.add(new Paragraph(new Text(String.valueOf(tr))));
            cel2.add(new Paragraph(new Text(purchaseProduct.getProduct().getName())));
            cel3.add(new Paragraph(new Text(String.valueOf(purchaseProduct.getProduct().getBarcode()))));
            cel4.add(new Paragraph(new Text(String.valueOf(purchaseProduct.getPurchasedQuantity()))));
            cel5.add(new Paragraph(new Text(String.valueOf(purchaseProduct.getProduct().getBuyPrice()))));
            cel6.add(new Paragraph(new Text(String.valueOf(purchaseProduct.getProduct().getTax()))));
            cel7.add(new Paragraph(new Text(String.valueOf(purchaseProduct.getProduct().getBuyPrice()*purchaseProduct.getPurchasedQuantity()+purchaseProduct.getProduct().getTax()))));
            tr++;
        }


        purchaseTable.addCell(cel1);
        purchaseTable.addCell(cel2);
        purchaseTable.addCell(cel3);
        purchaseTable.addCell(cel4);
        purchaseTable.addCell(cel5);
        purchaseTable.addCell(cel6);
        purchaseTable.addCell(cel7);
        purchaseTable.setWidth(new UnitValue(UnitValue.PERCENT, 100));
        purchaseTable.setTextAlignment(TextAlignment.CENTER);
        purchaseTable.setBorderTop(new SolidBorder(1));
        purchaseTable.setMarginTop(30);


        Table totalTable = new Table(2);
        Cell texts = new Cell();
        Cell values = new Cell();
        values.setBorder(Border.NO_BORDER);
        texts.setBorder(Border.NO_BORDER);

        double value1 = 0;
        double value3 = 0;
        for (PurchaseProduct purchaseProduct : purchase.getPurchaseProductList()) {
            value1=value1+purchaseProduct.getPurchasedQuantity()*purchaseProduct.getProduct().getBuyPrice();
            value3=value3+purchaseProduct.getProduct().getTax();
        }

        double value2 = purchase.getDeliveryPrice();

        Address address = purchase.getBranch().getAddress();
        String value4 = address.getCity() + ", "  + address.getDistrict() + ", "  + address.getStreet() + ", "  + address.getHome();

        String value5 = purchase.getDescription();

        Paragraph totalSum = new Paragraph().add(new Text("Maxsulotlar jami summasi: ").setBold().setFontSize(14)).setMarginTop(15);
        Paragraph totalValue = new Paragraph().add(new Text(String.valueOf(value1)).setFontSize(14)).setTextAlignment(TextAlignment.RIGHT).setMarginTop(15);

        Paragraph deliveryPrice = new Paragraph().add(new Text("Yetkazib berish narxi: ").setBold().setFontSize(14)).setMarginTop(15);
        Paragraph deliveryPriceValue = new Paragraph().add(new Text(String.valueOf(value2)).setFontSize(14)).setTextAlignment(TextAlignment.RIGHT).setMarginTop(15);

        Paragraph taxText = new Paragraph().add(new Text("Soliq: ").setBold().setFontSize(14)).setMarginTop(15);
        Paragraph taxValue = new Paragraph().add(new Text(String.valueOf(value3)).setFontSize(14)).setTextAlignment(TextAlignment.RIGHT).setMarginTop(15);

        Paragraph purchaseTotal = new Paragraph().add(new Text("Jami summa:  ").setBold().setFontSize(14)).setMarginTop(15);
        Paragraph purchaseTotalValue = new Paragraph().add(new Text(String.valueOf(value1+value2+value3)).setFontSize(14)).setTextAlignment(TextAlignment.RIGHT).setMarginTop(15);

        Paragraph addressText = new Paragraph().add(new Text("Address:  ").setBold().setFontSize(14)).setMarginTop(15);
        Paragraph addressValue = new Paragraph().add(new Text(value4).setFontSize(14)).setTextAlignment(TextAlignment.RIGHT).setMarginTop(15);

        Paragraph descritptionText = new Paragraph().add(new Text("Qisqa eslatma:  ").setBold().setFontSize(14)).setMarginTop(15);
        Paragraph descritptionValue = new Paragraph().add(new Text(value5).setFontSize(14)).setTextAlignment(TextAlignment.RIGHT).setMarginTop(15);
/**
 */

        texts.add(totalSum);
        values.add(totalValue);

        texts.add(deliveryPrice);
        values.add(deliveryPriceValue);

        texts.add(taxText);
        values.add(taxValue);

        texts.add(purchaseTotal);
        values.add(purchaseTotalValue);

        texts.add(addressText);
        values.add(addressValue);

        texts.add(descritptionText);
        values.add(descritptionValue);


        totalTable.addCell(texts);
        totalTable.addCell(values);
        totalTable.setWidth(new UnitValue(UnitValue.PERCENT, 100));
        totalTable.setMarginTop(30);

        document.add(paragraph);
        document.add(chek);
        document.add(table1);
        document.add(purchaseTable);
        document.add(totalTable);
        document.close();


        File file = new File(path);
        byte[] aByte = getByte(file);

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());

        long size = Files.size(Path.of(path));

        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", file.getName() + "/:" + size);
        FileCopyUtils.copy(aByte, response.getOutputStream());

        file.delete();
    }

}
