package VSim.SimInterface;

import VSim.Types.EventType;
import VSim.Types.Quarter;

public interface EventObserver {
    void update(Quarter quarterlyEvent);

}
