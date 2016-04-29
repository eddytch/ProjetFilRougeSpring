package fr.iocean.application.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by utilisateur on 27/04/2016.
 */
@Getter
@Setter
@Component
public class PageableImpl implements Pageable {

    private int currentPage = 0 ;
    private int  nbPerPage = 10 ;
    

    @Override
    public int getPageNumber() {
        return currentPage;
    }

    @Override
    public int getPageSize() {
        return nbPerPage;
    }

    @Override
    public int getOffset() {
        return currentPage*nbPerPage;
    }

    @Override
    public Sort getSort() {
        return new Sort(Sort.Direction.ASC,"id");
    }

    @Override
    public Pageable next() {
        return new PageRequest(currentPage+1,nbPerPage) ;
    }

    @Override
    public Pageable previousOrFirst() {
    	if(currentPage>0)
    		return new PageRequest(currentPage-1,nbPerPage) ;
    	else 
    		return first() ;
    }

    @Override
    public Pageable first() {
    	return new PageRequest(0, nbPerPage) ;
    }

    @Override
    public boolean hasPrevious() {
        return currentPage > 0;
    }
}
