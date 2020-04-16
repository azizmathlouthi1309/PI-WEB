<?php

namespace ActivityBundle\EventListener;

use ActivityBundle\Entity\Activity;
use Toiba\FullCalendarBundle\Entity\Event;
use Toiba\FullCalendarBundle\Event\CalendarEvent;
use ActivityBundle\Repository\ActivityRepository;
use Doctrine\ORM\EntityManagerInterface ;
class FullCalendarListener
{


    /**
     * @var EntityManagerInterface
     */
    private $em;

    public function __construct(EntityManagerInterface $em)
    {
        $this->em = $em;
    }

    public function loadEvents(CalendarEvent $calendar)
    {
        $startDate = $calendar->getStart();
        $endDate = $calendar->getEnd();
        $filters = $calendar->getFilters();


        $activities = $this->em->getRepository("ActivityBundle:Activity")->findAll();


        foreach ($activities as $act)
        {
            $calendar->addEvent(new Event(
                $act->getName(),
                $act->getDateStart(),
                $act->getDateEnd()
            ));
        }




    }
}