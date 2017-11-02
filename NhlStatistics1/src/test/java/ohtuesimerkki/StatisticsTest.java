package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void searchTestNull() {
        assertEquals(null, stats.search("Crosby"));
    }
    
    @Test
    public void searchTestFind() {
        Player p = stats.search("emenko");
        assertEquals("Semenko", p.getName());
        assertEquals("EDM", p.getTeam());
        assertEquals(4, p.getGoals());
        assertEquals(12, p.getAssists());
    }
    
    @Test
    public void testTeam() {
        List<Player> ps = stats.team("EDM");
        assertEquals(3, ps.size());
        List<String> ns = new ArrayList<String>();
        for (Player p : ps) {
            ns.add(p.getName());
        }
        assertTrue(ns.contains("Semenko") && ns.contains("Kurri") && ns.contains("Gretzky"));
    }
    
    @Test
    public void testTopScorers() {
        List<Player> ps = stats.topScorers(2);
        assertEquals(2, ps.size());
        List<String> ns = new ArrayList<String>();
        for (Player p : ps) {
            ns.add(p.getName());
        }
        assertTrue(ns.contains("Lemieux") && ns.contains("Gretzky"));
    }
}
