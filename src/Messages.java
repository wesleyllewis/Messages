import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by WesleyLewis on 9/20/16.
 */
public class Messages {
    static User user;
    static Message message;

    public static void main(String[] args) {
        ArrayList<Message> messageList = new ArrayList<>();
        Spark.init();

        Spark.get("/", ((request, response) -> {
                    HashMap nameMap = new HashMap();
                    if (user == null) {
                        return new ModelAndView(nameMap, "index.html");
                    } else {
                        nameMap.put("name", user.name);
                        nameMap.put("messages", messageList);
                        return new ModelAndView(nameMap, "messages.html");
                    }
                }),
                new MustacheTemplateEngine()
        );

        Spark.post(
                "/create-user",
                (((request, response) -> {
                    String userName = request.queryParams("userName");
                    user = new User(userName);
                    response.redirect("/");

                    return "";
                }))
        );
        Spark.get(
                "/",
                (((request, response) -> {
                    if (message == null) {
                        return new ModelAndView(messageList, "messages.html");
                    } else {
                        messageList.add(message);
                        return new ModelAndView(messageList, "messages.html");
                    }

                })),
                new MustacheTemplateEngine()
        );
        Spark.post(
                "/create-message",
                (((request, response) -> {
                    String userMessage = request.queryParams("userMessage");
                    message = new Message(userMessage);
                    messageList.add(message);
                    response.redirect("/");

                    return "";
                }))
        );

    }
}
