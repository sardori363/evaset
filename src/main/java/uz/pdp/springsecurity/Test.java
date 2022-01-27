package uz.pdp.springsecurity;

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

import java.io.FileNotFoundException;


public class Test {
    static String path = "src/main/resources/invoice.pdf";

    public static void main(String[] args) throws FileNotFoundException {

        PdfWriter writer = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(writer);
        pdfDocument.addNewPage();
        Document document = new Document(pdfDocument);

        Paragraph paragraph = new Paragraph("purchase.getBranch().getName()");
        paragraph.setFontSize(40);
        paragraph.setTextAlignment(TextAlignment.CENTER);

        Text text = new Text("Chek");
        text.setFontSize(24);

        Paragraph chek = new Paragraph(text);
        chek.setTextAlignment(TextAlignment.CENTER);

        Text id = new Text(String.valueOf("purchase.getId()"));
        Text text1 = new Text("Chek N: ");
        text1.setBold();
        text1.setFontSize(14);
        Paragraph purchaseId = new Paragraph();
        purchaseId.add(text1);
        purchaseId.add(id);


        String dateDB = String.valueOf("purchase.getDate()");
        String substring = dateDB.substring(0, 16);
        Paragraph date = new Paragraph();
        Text dateTime = new Text(substring);
        Text dateText = new Text("Sana: ");
        dateText.setBold();
        dateText.setFontSize(14);
        date.add(dateText);
        date.add(dateTime);
        date.setMarginTop(15);

        Paragraph customer = new Paragraph();
        Text custValue = new Text("purchase.getDealer().getName()");
        Text custText = new Text("Ta'minotchi: ");
        custText.setFontSize(14);
        custText.setBold();
        customer.add(custText);
        customer.add(custValue);

        Paragraph seller = new Paragraph();
        Text sellerText = new Text("Qabul qiluvchi: ");
        Text sellerValue = new Text("purchase.getSeller().getFirstName()");
        sellerText.setFontSize(14);
        sellerText.setBold();
        seller.add(sellerText);
        seller.add(sellerValue);
        seller.setMarginTop(15);

        Paragraph phoneNumber = new Paragraph();
        Text phoneText = new Text("Telefon raqami: ");
        Text phoneValue = new Text("purchase.getDealer().getPhoneNumber");
        phoneText.setFontSize(14);
        phoneText.setBold();
        phoneNumber.add(phoneText);
        phoneNumber.add(phoneValue);
        phoneNumber.setMarginTop(15);

        Paragraph payStatus = new Paragraph();
        Text payStatusText = new Text("To'lov statusi: ");
        Text payStatusValue = new Text("purchase.getPayStatus()");
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


        Table purchaseTable = new Table(7);
        Cell cel1 = new Cell().add(new Paragraph(new Text("T/r").setBold().setFontSize(14)));
        Cell cel2 = new Cell().add(new Paragraph(new Text("Maxsulot nomi").setBold().setFontSize(14)));
        Cell cel3 = new Cell().add(new Paragraph(new Text("Shtrix kodi").setBold().setFontSize(14)));
        Cell cel4 = new Cell().add(new Paragraph(new Text("Miqdori").setBold().setFontSize(14)));
        Cell cel5 = new Cell().add(new Paragraph(new Text("Narxi").setBold().setFontSize(14)));
        Cell cel6 = new Cell().add(new Paragraph(new Text("Soliq").setBold().setFontSize(14)));
        Cell cel7 = new Cell().add(new Paragraph(new Text("Sotilish narxi").setBold().setFontSize(14)));
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


        Paragraph totalSum = new Paragraph().add(new Text("Maxsulotlar jami summasi: ").setBold().setFontSize(14)).setMarginTop(15);
        Paragraph totalValue = new Paragraph().add(new Text("[value]").setFontSize(14)).setTextAlignment(TextAlignment.RIGHT).setMarginTop(15);

        Paragraph deliveryPrice = new Paragraph().add(new Text("Yetkazib berish narxi: ").setBold().setFontSize(14)).setMarginTop(15);
        Paragraph deliveryPriceValue = new Paragraph().add(new Text("[value]").setFontSize(14)).setTextAlignment(TextAlignment.RIGHT).setMarginTop(15);

        Paragraph taxText = new Paragraph().add(new Text("Soliq: ").setBold().setFontSize(14)).setMarginTop(15);
        Paragraph taxValue = new Paragraph().add(new Text("[value]").setFontSize(14)).setTextAlignment(TextAlignment.RIGHT).setMarginTop(15);

        Paragraph purchaseTotal = new Paragraph().add(new Text("Jami summa:  ").setBold().setFontSize(14)).setMarginTop(15);
        Paragraph purchaseTotalValue = new Paragraph().add(new Text("[value]").setFontSize(14)).setTextAlignment(TextAlignment.RIGHT).setMarginTop(15);

        Paragraph addressText = new Paragraph().add(new Text("Address:  ").setBold().setFontSize(14)).setMarginTop(15);
        Paragraph addressValue = new Paragraph().add(new Text("[value]").setFontSize(14)).setTextAlignment(TextAlignment.RIGHT).setMarginTop(15);
/**
 * "address.getCity() + ,  + address.getDistrict() + ,  + address.getStreet() + ,  + address.getHome()"
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


    }

}
