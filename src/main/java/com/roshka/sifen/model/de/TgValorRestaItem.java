package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TTImp;
import com.roshka.sifen.model.de.types.TTiDE;
import com.roshka.sifen.model.de.types.TdCondTiCam;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TgValorRestaItem {
    private BigDecimal dDescItem;
    private BigDecimal dPorcDesIt;
    private BigDecimal dDescGloItem;
    private BigDecimal dAntPreUniIt;
    private BigDecimal dAntGloPreUniIt;
    private BigDecimal dTotOpeItem;
    private BigDecimal dTotOpeGs;

    public void setupSOAPElements(SOAPElement gValorItem, TTiDE iTiDE, TTImp iTImp, TdCondTiCam dCondTiCam, BigDecimal dTiCamIt,
                                  BigDecimal dPUniProSer, BigDecimal dCantProSer) throws SOAPException {
        SOAPElement gValorRestaItem = gValorItem.addChildElement("gValorRestaItem");

        if (this.dDescItem != null) {
            gValorRestaItem.addChildElement("dDescItem").setTextContent(String.valueOf(this.dDescItem));

            this.dPorcDesIt = (this.dDescItem.multiply(BigDecimal.valueOf(100))).divide(dPUniProSer, RoundingMode.HALF_UP);
            gValorRestaItem.addChildElement("dPorcDesIt").setTextContent(String.valueOf(this.dPorcDesIt));
        }

        if (this.dDescGloItem != null)
            gValorRestaItem.addChildElement("dDescGloItem").setTextContent(String.valueOf(this.dDescGloItem));

        gValorRestaItem.addChildElement("dAntPreUniIt").setTextContent(
                this.dAntPreUniIt != null ? String.valueOf(this.dAntPreUniIt) : "0"
        );

        gValorRestaItem.addChildElement("dAntGloPreUniIt").setTextContent(
                this.dAntGloPreUniIt != null ? String.valueOf(this.dAntGloPreUniIt) : "0"
        );

        if (iTiDE.getVal() == 4) {
            this.dTotOpeItem = dPUniProSer.multiply(dCantProSer);
        } else if (iTImp.getVal() == 1 || iTImp.getVal() == 3 || iTImp.getVal() == 4 || iTImp.getVal() == 5) {
            this.dTotOpeItem = (dPUniProSer.subtract(this.dDescItem != null ? this.dDescItem : BigDecimal.valueOf(0))
                    .subtract(this.dDescGloItem != null ? this.dDescGloItem : BigDecimal.valueOf(0))
                    .subtract(this.dAntPreUniIt != null ? this.dAntPreUniIt : BigDecimal.valueOf(0))
                    .subtract(this.dAntGloPreUniIt != null ? this.dAntGloPreUniIt : BigDecimal.valueOf(0)))
                    .multiply(dCantProSer);
        }
        gValorRestaItem.addChildElement("dTotOpeItem").setTextContent(String.valueOf(this.dTotOpeItem));

        if (dCondTiCam != null && dCondTiCam.getVal() == 2) {
            this.dTotOpeGs = this.dTotOpeItem.multiply(dTiCamIt);
            gValorRestaItem.addChildElement("dTotOpeGs").setTextContent(String.valueOf(this.dTotOpeGs));
        }
    }

    public BigDecimal getdDescItem() {
        return dDescItem;
    }

    public void setdDescItem(BigDecimal dDescItem) {
        this.dDescItem = dDescItem;
    }

    public BigDecimal getdPorcDesIt() {
        return dPorcDesIt;
    }

    public BigDecimal getdDescGloItem() {
        return dDescGloItem;
    }

    public void setdDescGloItem(BigDecimal dDescGloItem) {
        this.dDescGloItem = dDescGloItem;
    }

    public BigDecimal getdAntPreUniIt() {
        return dAntPreUniIt;
    }

    public void setdAntPreUniIt(BigDecimal dAntPreUniIt) {
        this.dAntPreUniIt = dAntPreUniIt;
    }

    public BigDecimal getdAntGloPreUniIt() {
        return dAntGloPreUniIt;
    }

    public void setdAntGloPreUniIt(BigDecimal dAntGloPreUniIt) {
        this.dAntGloPreUniIt = dAntGloPreUniIt;
    }

    public BigDecimal getdTotOpeItem() {
        return dTotOpeItem;
    }

    public BigDecimal getdTotOpeGs() {
        return dTotOpeGs;
    }
}
