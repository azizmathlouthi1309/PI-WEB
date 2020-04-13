<?php

namespace EventBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Participation
 *
 * @ORM\Table(name="participation", indexes={@ORM\Index(name="IDX_AB55E24F71F7E88B", columns={"event_id"})})
 * @ORM\Entity
 */
class Participation
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\User", inversedBy="participation")
     * @ORM\JoinColumn(name="parent_id", referencedColumnName="id")
     * @ORM\GeneratedValue(strategy="NONE")
     */
    private $parentId;


    /**
     * @var \Event
     *
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="Event")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="event_id", referencedColumnName="id")
     * })
     */
    private $event;

    /**
     * @return int
     */
    public function getParentId()
    {
        return $this->parentId;
    }

    /**
     * @param int $parentId
     */
    public function setParentId($parentId)
    {
        $this->parentId = $parentId;
    }

    /**
     * @return \Event
     */
    public function getEvent()
    {
        return $this->event;
    }

    /**
     * @param \Event $event
     */
    public function setEvent($event)
    {
        $this->event = $event;
    }


}

