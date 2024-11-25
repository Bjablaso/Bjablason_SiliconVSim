package vsim.siminterface;

import vsim.core.StartUp;
import vsim.types.Quarter;

public interface StartUpInterface {
    void levelUp();
    void applyEventEffect(Quarter event);
     void attack(StartUp target);
}
