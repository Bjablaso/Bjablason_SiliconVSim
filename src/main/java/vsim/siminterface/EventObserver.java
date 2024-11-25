package vsim.siminterface;

import vsim.types.Quarter;

public interface EventObserver {
    void update(Quarter quarterlyEvent);

}
