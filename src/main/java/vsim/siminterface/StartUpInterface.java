package vsim.siminterface;

import vsim.core.StartUp;
import vsim.types.Quarter;

public interface StartUpInterface {
    void levelUp(int valueGain);
    void applyEventEffect(Quarter event);
}
