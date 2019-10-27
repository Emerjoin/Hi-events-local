package org.emerjoin.hi.web.events;

import org.emerjoin.hi.web.events.sse.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * @author Mario Junior.
 */
@ApplicationScoped
public class VMScopedWebEventsContext implements WebEventsContext {

    @Inject
    private WebEventsController eventsController;

    @Override
    public void publish(WebEvent event) {
        WebEventPublishRequest request = new WebEventPublishRequest(event);
        eventsController.execute(request);
    }

    @Override
    public void publish(WebEvent event, String... channels) {
        WebEventPublishRequest request = new WebEventPublishRequest(event, channels);
        eventsController.execute(request);
    }

    public void onJoinChannel(@Observes JoinChannel event){
        eventsController.joinChannel(event.getUser().getUniqueId(),
                event.getChannel());
    }

    public void onQuitChannel(@Observes QuitChannel event){
        eventsController.quitChannel(event.getUser().getUniqueId(),event.
                getChannel());
    }

}
