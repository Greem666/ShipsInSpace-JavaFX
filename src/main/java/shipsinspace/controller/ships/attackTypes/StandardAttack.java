package shipsinspace.controller.ships.attackTypes;

import shipsinspace.common.Coordinates;

import java.util.Objects;

public class StandardAttack implements Attack {
    Effect effect;
    @Override
    public Effect attack(Coordinates coordinates) {
        this.effect = new Effect(coordinates, "destroy");
        return this.effect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandardAttack that = (StandardAttack) o;
        return Objects.equals(effect, that.effect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(effect);
    }

    @Override
    public String toString() {
        return "StandardAttack";
    }
}
