package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaVarastoonNegatiivinenMaara() {
        varasto.lisaaVarastoon(-1);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaVarastoonLiikaa() {
        varasto.lisaaVarastoon(100);
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        varasto.otaVarastosta(1);
        assertEquals(1, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaVarastoonSopivasti() {
        varasto.lisaaVarastoon(4);
        assertEquals(6, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaNegatiivinenMaara() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-4);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaLiikaa() {
        varasto.lisaaVarastoon(5);
        double taken = varasto.otaVarastosta(6);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(5, taken, vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaSopivasti() {
        varasto.lisaaVarastoon(5);
        double taken = varasto.otaVarastosta(2);
        assertEquals(3, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(1, taken, vertailuTarkkuus);
    }

    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1,1);
        varasto = new Varasto(1,2);
        varasto = new Varasto(-1,2);
        varasto = new Varasto(-1,-1);
        varasto.toString();
    }
}