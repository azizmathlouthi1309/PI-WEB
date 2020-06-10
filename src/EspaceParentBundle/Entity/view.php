<?php

namespace EspaceParentBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * view
 *
 * @ORM\Table(name="view")
 * @ORM\Entity(repositoryClass="EspaceParentBundle\Repository\viewRepository")
 */
class view
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var int
     *
     * @ORM\Column(name="nb_stars", type="integer")
     */
    private $nbStars;

    /**
     * @var string
     *
     * @ORM\Column(name="subject", type="string", length=255)
     */
    private $subject;

    /**
     * @var string
     *
     * @ORM\Column(name="message", type="string", length=255)
     */
    private $message;

    /**
     * @var string
     *
     * @ORM\Column(name="SenderName", type="string", length=255)
     */
    private $senderName;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set nbStars
     *
     * @param integer $nbStars
     *
     * @return view
     */
    public function setNbStars($nbStars)
    {
        $this->nbStars = $nbStars;

        return $this;
    }

    /**
     * Get nbStars
     *
     * @return int
     */
    public function getNbStars()
    {
        return $this->nbStars;
    }

    /**
     * Set subject
     *
     * @param string $subject
     *
     * @return view
     */
    public function setSubject($subject)
    {
        $this->subject = $subject;

        return $this;
    }

    /**
     * Get subject
     *
     * @return string
     */
    public function getSubject()
    {
        return $this->subject;
    }

    /**
     * Set message
     *
     * @param string $message
     *
     * @return view
     */
    public function setMessage($message)
    {
        $this->message = $message;

        return $this;
    }

    /**
     * Get message
     *
     * @return string
     */
    public function getMessage()
    {
        return $this->message;
    }

    /**
     * Set senderName
     *
     * @param string $senderName
     *
     * @return view
     */
    public function setSenderName($senderName)
    {
        $this->senderName = $senderName;

        return $this;
    }

    /**
     * Get senderName
     *
     * @return string
     */
    public function getSenderName()
    {
        return $this->senderName;
    }
}

