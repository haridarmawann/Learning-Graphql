package hari.core.graphql.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import hari.core.graphql.models.Author;
import hari.core.graphql.models.Tutorial;
import hari.core.graphql.repository.AuthorRepository;
import hari.core.graphql.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class Mutation implements GraphQLMutationResolver {
    final private AuthorRepository authorRepository;
    final private TutorialRepository tutorialRepository;


    public Mutation(AuthorRepository authorRepository, TutorialRepository tutorialRepository) {
        this.authorRepository = authorRepository;
        this.tutorialRepository = tutorialRepository;
    }
    @MutationMapping
    public Author createAuthor(@Argument String name, @Argument Integer age) {
        Author author = new Author();
        author.setName(name);
        author.setAge(age);

        authorRepository.save(author);

        return author;
    }
    @MutationMapping
    public Tutorial createTutorial(@Argument String title,@Argument String description,@Argument String authorId) {
        Tutorial book = new Tutorial(title,description,authorId);
        tutorialRepository.save(book);
        return book;
    }


    @MutationMapping
    public boolean deleteTutorial(String id) {
        tutorialRepository.deleteById(id);
        return true;
    }

    @MutationMapping
    public Tutorial updateTutorial(String id, String title, String description) throws Exception {
        Optional<Tutorial> optTutorial = tutorialRepository.findById(id);

        if (optTutorial.isPresent()) {
            Tutorial tutorial = optTutorial.get();

            if (title != null)
                tutorial.setTitle(title);
            if (description != null)
                tutorial.setDescription(description);

            tutorialRepository.save(tutorial);
            return tutorial;
        }

        throw new Exception("Not found Tutorial to update!");
    }


}
