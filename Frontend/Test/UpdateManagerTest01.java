import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import Classes.Movie;
import Functionality.UpdateManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import Panels.AdminPageAddFilm;

import static org.junit.jupiter.api.Assertions.assertEquals;



class UpdateManagerTest01 {

    @Test
    void updateEmployee() {
    }

    UpdateManager updater;

    @BeforeEach
    public void initialize(){
        this.updater = new UpdateManager();
    }

    @Test
    public void updateMovie(){
        //input
        String inputs = "ID:1,Name:Batman";
        Movie movie = new Movie(1,"Spiderman","Action","90","lalala",
                "lululu","test",true);

        //when
        movie = this.updater.updateMovie(inputs);

        //result
        assertEquals("Batman",movie.getName());
    }

    @Test
    public void updateMovie2(){
        //input
        String inputs = "Name:Batman";
        Movie movie = new Movie(1,"Spiderman","Action","90","lalala",
                "lululu","test",true);

        //when
        movie = this.updater.updateMovie(inputs);

        //result
        assertNull(movie);
    }

    @Test
    public void updateMovie3(){
        //input
        String inputs = "ID:1,Name:Batman,Available:False";
        Movie movie = new Movie(1,"Spiderman","Action","90","lalala",
                "lululu","test",true);

        //when
        movie = this.updater.updateMovie(inputs);

        //result
        assertFalse(movie.isAvailable());
    }

    @Test
    public void updateMovie4(){
        //input
        String inputs = "ID:Hallo,Name:Batman";
        Movie movie = new Movie(1,"Spiderman","Action","90","lalala",
                "lululu","test",true);

        //when
        movie = this.updater.updateMovie(inputs);

        //result
        assertNull(movie);
    }
    @Test
    public void updateMovie5(){
        //input
        String inputs = "ID:1,Name:Batman,";
        Movie movie = new Movie(1,"Spiderman","Action","90","lalala",
                "lululu","test",true);

        //when
        movie = this.updater.updateMovie(inputs);

        //result
        assertEquals("Batman",movie.getName());
    }
}