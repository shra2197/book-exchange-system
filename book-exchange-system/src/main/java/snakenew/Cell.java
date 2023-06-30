package snakenew;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Cell {
    @NonNull
    private int x;
    @NonNull
    private int y;
    CellType type;

    @Override
    public String toString() {
        return x + ":" + y;
    }
}
