package hari.core.graphql.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import hari.core.graphql.models.Author;
import hari.core.graphql.models.Tutorial;
import hari.core.graphql.repository.AuthorRepository;
import hari.core.graphql.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Query implements GraphQLQueryResolver {

    final private AuthorRepository authorRepository;
    final private TutorialRepository tutorialRepository;


    public Query(AuthorRepository authorRepository, TutorialRepository tutorialRepository) {
        this.authorRepository = authorRepository;
        this.tutorialRepository = tutorialRepository;
    }
    @QueryMapping
    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    public Iterable<Tutorial> findAllTutorials() {
        return tutorialRepository.findAll();
    }

    @QueryMapping
    public long countAuthors() {
        return authorRepository.count();
    }

    @QueryMapping
    public long countTutorials() {
        return tutorialRepository.count();
    }

}
