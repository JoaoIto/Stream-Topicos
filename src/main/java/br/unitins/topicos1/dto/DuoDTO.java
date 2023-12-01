package br.unitins.topicos1.dto;
import java.util.List;
import br.unitins.topicos1.model.Game;
import jakarta.validation.constraints.NotBlank;


public class DuoDTO {
    @NotBlank
    private final StreamDTO stream;
    @NotBlank
    private final List<Game> listaGame;

    
    public DuoDTO(@NotBlank StreamDTO stream, @NotBlank List<Game> listaGame) {
        this.stream = stream;
        this.listaGame = listaGame;
    }

    public StreamDTO getStream() {
        return stream;
    }

    public List<Game> getListaGame() {
        return listaGame;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((stream == null) ? 0 : stream.hashCode());
        result = prime * result + ((listaGame == null) ? 0 : listaGame.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DuoDTO other = (DuoDTO) obj;
        if (stream == null) {
            if (other.stream != null)
                return false;
        } else if (!stream.equals(other.stream))
            return false;
        if (listaGame == null) {
            if (other.listaGame != null)
                return false;
        } else if (!listaGame.equals(other.listaGame))
            return false;
        return true;
    }

    


    
}
