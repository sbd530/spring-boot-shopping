package com.don.shopping.domains.review.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class ReviewRepository implements JpaRepository<ReviewEntity, Long> {
    @Override
    public List<ReviewEntity> findAll() {
        return null;
    }

    @Override
    public List<ReviewEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ReviewEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<ReviewEntity> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(ReviewEntity entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends ReviewEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends ReviewEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ReviewEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ReviewEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends ReviewEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<ReviewEntity> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ReviewEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends ReviewEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ReviewEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ReviewEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ReviewEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ReviewEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ReviewEntity> boolean exists(Example<S> example) {
        return false;
    }
}
