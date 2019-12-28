package ua.nure.cs.galayda.usermanagement.agent;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ua.nure.cs.galayda.usermanagement.db.DAOFactory;
import ua.nure.cs.galayda.usermanagement.db.DatabaseException;
import ua.nure.cs.galayda.usermanagement.entity.User;

import java.util.*;

public class RequestServer extends CyclicBehaviour {

    @Override
    public void action() {
        ACLMessage message = myAgent.receive();
        if (message != null) {
            if (message.getPerformative() == ACLMessage.REQUEST) {
                myAgent.send(CreateReply(message));
            } else {
                Collection users = ParseMessage(message);
                ((SearchAgent) myAgent).ShowUsers(users);
            }
        } else {
            block();
        }
    }

    private Collection ParseMessage(ACLMessage message) {
        Collection systemUsers = new LinkedList();

        String content = message.getContent();
        if (content != null) {
            StringTokenizer tokenizer = new StringTokenizer(content, ";");
            while (tokenizer.hasMoreTokens()) {
                String userInfo = tokenizer.nextToken();
                StringTokenizer innerTokenizer = new StringTokenizer(userInfo, ",");
                String id = innerTokenizer.nextToken();
                String firstName = innerTokenizer.nextToken();
                String lastName = innerTokenizer.nextToken();
                User newSystemUser = new User();
                newSystemUser.setId(new Long(id));
                newSystemUser.setFirstName(firstName);
                newSystemUser.setLastName(lastName);
                newSystemUser.setDateOfBirth(null);
                systemUsers.add(newSystemUser);
            }
        }
        return systemUsers;
    }

    private ACLMessage CreateReply(ACLMessage message) {
        ACLMessage reply = message.createReply();

        String content = message.getContent();
        StringTokenizer tokenizer = new StringTokenizer(content, ",");
        if (tokenizer.countTokens() == 2) {
            String firstName = tokenizer.nextToken();
            String lastName = tokenizer.nextToken();
            Collection users;
            try {
                users = DAOFactory
                        .getInstance().getUserDao().find(firstName, lastName);
            } catch (DatabaseException e) {
                users = new ArrayList(0);
            }

            StringBuffer buffer = new StringBuffer();
            for (Iterator it = users.iterator(); it.hasNext();) {
                User systemUser = (User) it.next();
                buffer.append(systemUser.getId()).append(",");
                buffer.append(systemUser.getFirstName()).append(",");
                buffer.append(systemUser.getLastName()).append(";");
            }
            reply.setContent(buffer.toString());
        }
        return reply;
    }
}