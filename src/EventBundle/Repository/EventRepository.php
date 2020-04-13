<?php


namespace EventBundle\Repository;


use Composer\DependencyResolver\Request;
use Doctrine\ORM\EntityRepository;
use EventBundle\EventBundle\Entity\Event;
use Doctrine\ORM\Query;
class EventRepository extends EntityRepository
{
    /**
     * get all events
     *
     * @return array
     */
    public function findAllPosts()
    {
        return $this->getEntityManager()
            ->createQuery(
                'SELECT e
         FROM EventBundle:Event e
      
         ORDER BY e.date DESC'
            )
            ->getArrayResult();
    }

    public function findEntitiesByString($str){
        return $this->getEntityManager()
            ->createQuery(
                'SELECT e
                FROM EventBundle:Event e
                WHERE e.name LIKE :str'
            )
            ->setParameter('str', '%'.$str.'%')
            ->getResult();
    }
    public function findById($id)
    {
        try {
            return $this->getEntityManager()
                ->createQuery(
                    "SELECT e
                FROM EventBundle:Event
                 WHERE e.id =:id"
                )
                ->setParameter('id', $id)
                ->getOneOrNullResult();
        } catch (NonUniqueResultException $e) {
        }
    }


}